package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Area;
import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Entity.Enterprise;
import com.lofton.nom35.Entity.Job;
import com.lofton.nom35.Entity.Survey;
import com.lofton.nom35.Repository.AreaRepository;
import com.lofton.nom35.Repository.BranchRepository;
import com.lofton.nom35.Repository.EnterpriseRepository;
import com.lofton.nom35.Repository.JobRepository;
import com.lofton.nom35.Repository.SurveyRepository;
import com.lofton.nom35.process.branch.BranchValidator;
import com.lofton.nom35.process.branch.MinRespondents;
import com.lofton.nom35.templates.BranchAreasJobs;
import com.lofton.nom35.templates.BranchAreasJobsUpdate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class BranchController {

    private final BranchRepository branchRepository;

    private final EnterpriseRepository enterpriseRepository;

    private final SurveyRepository surveyRepo;

    private final AreaRepository areaRepo;

    private final JobRepository jobRepo;

    @Autowired
    public BranchController(BranchRepository branchRepository, EnterpriseRepository enterpriseRepository, SurveyRepository surveyRepo, AreaRepository areaRepo, JobRepository jobRepo) {
        this.branchRepository = branchRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.surveyRepo = surveyRepo;
        this.areaRepo = areaRepo;
        this.jobRepo = jobRepo;
    }

    @GetMapping("/branches")
    public List<Branch> getFindAll(@RequestHeader String Authorization) {
        return branchRepository.findAll();
    }

    @GetMapping("/branches/{branchId}")
    public Branch getBranch(@RequestHeader String Authorization, @PathVariable int branchId) {
        Optional<Branch> result = branchRepository.findById(branchId);
        Branch theBranch = null;
        if (result.isPresent()) {
            theBranch = result.get();
        } else {
            throw new RuntimeException("Did not find branch id - " + branchId);
        }
        return theBranch;
    }

    //GUARDAR EMPRESA CON AREAS Y WORKS
    @PostMapping("/branches/{enterpriseId}")
    public List<Branch> addBranchesWAreasWJobs(
            @RequestHeader String Authorization,
            @RequestBody List<BranchAreasJobs> Branches,
            @PathVariable int enterpriseId) {

        //validar que no agregen mas branches de las especificadas en encuesta
        //traemos la empresa y el num de branches
        Optional<Enterprise> tempEnter = enterpriseRepository.findById(enterpriseId);
        Enterprise theEnter = null;

        if (tempEnter.isPresent()) {
            theEnter = tempEnter.get();
            if (theEnter.getMax_branch() < theEnter.getBranches().size() + Branches.size()) {
                throw new CustomBadRequestException("El numero de Centros de Trabajo excede el maximo establecido");
            }
        } else {
            throw new CustomNotFoundException("No se encontro la empresa especificada");
        }

        //Traemos las encuestas
        Optional<Survey> tempEncuesta1 = surveyRepo.findById(1);
        Optional<Survey> tempEncuesta2 = surveyRepo.findById(2);
        Optional<Survey> tempEncuesta3 = surveyRepo.findById(3);
        Survey encuesta1 = null;
        Survey encuesta2 = null;
        Survey encuesta3 = null;
        if (tempEncuesta1.isPresent() && tempEncuesta2.isPresent() && tempEncuesta3.isPresent()) {
            encuesta1 = tempEncuesta1.get();
            encuesta2 = tempEncuesta2.get();
            encuesta3 = tempEncuesta3.get();
        }

        //traemos la empresa
        Optional<Enterprise> tempEnterprise = enterpriseRepository.findById(enterpriseId);

        Enterprise theEnterprise = null;

        if (tempEnterprise.isPresent()) {

            theEnterprise = tempEnterprise.get();

        } else {
            throw new RuntimeException("Enterprise id not found - " + enterpriseId);
        }

        //tomamos accion en cada branch
        for (BranchAreasJobs BAJ : Branches) {
            Branch theBranch = new Branch();
            theBranch.setId(0);
            theBranch.setStatus(Boolean.TRUE);
            theBranch.setEmployees(BAJ.getBranch().getEmployees());
            theBranch.setAddress(BAJ.getBranch().getAddress());
            theBranch.setName(BAJ.getBranch().getName());
            theBranch.setBranchType(BAJ.getBranch().getBranchType());

            if (theBranch.getEmployees() <= 15) {
                theBranch.addSurvey(encuesta1);
            } else if (theBranch.getEmployees() <= 50) {
                theBranch.addSurvey(encuesta1);
                theBranch.addSurvey(encuesta2);
            } else {
                theBranch.addSurvey(encuesta1);
                theBranch.addSurvey(encuesta3);
            }

            if (theBranch.getEmployees() <= 50) {
                theBranch.setMin_respondents(theBranch.getEmployees());
            } else {
                theBranch.setMin_respondents(MinRespondents.calculateMin(theBranch.getEmployees()));
            }

            theBranch.setEnterprise(theEnterprise);

            String validator = BranchValidator.createCalculator();

            theBranch.setValidator(validator);

            List<Area> areas = new ArrayList<>();

            List<Integer> listAreas = BAJ.getAreas();

            for (Integer area : listAreas) {
                Optional<Area> tempArea = areaRepo.findById(area);
                Area theArea = null;
                if (tempArea.isPresent()) {
                    theArea = tempArea.get();
                }
                areas.add(theArea);
            }

            theBranch.setAreas(areas);

            //
            List<Job> jobs = new ArrayList<>();

            List<Integer> listJobs = BAJ.getJobs();

            for (Integer job : listJobs) {
                Optional<Job> tempJob = jobRepo.findById(job);
                Job theJob = null;
                if (tempJob.isPresent()) {
                    theJob = tempJob.get();
                }
                jobs.add(theJob);
            }

            theBranch.setJobs(jobs);

            branchRepository.save(theBranch);
        }

        Optional<Enterprise> tempEnterprise1 = enterpriseRepository.findById(enterpriseId);

        Enterprise theEnterprise1 = null;

        if (tempEnterprise1.isPresent()) {

            theEnterprise1 = tempEnterprise1.get();

        } else {
            throw new RuntimeException("Enterprise id not found - " + enterpriseId);
        }

        return theEnterprise1.getBranches();
    }

    @PutMapping("/branches")
    public String updateBranch(@RequestHeader String Authorization, @RequestBody List<BranchAreasJobsUpdate> Branches) {

        //para cada elemento en el arreglo
        for (BranchAreasJobsUpdate BAJ : Branches) {
            // traemos el branch
            Optional<Branch> tempBranch = branchRepository.findById(BAJ.getIdbranch());

            Branch theBranch = null;

            if (tempBranch.isPresent()) {
                theBranch = tempBranch.get();
            } else {
                throw new CustomNotFoundException("el Centro de trabajo solicitado no existe");
            }
            if (theBranch.getEmployees() <= 50) {
                theBranch.setMin_respondents(theBranch.getEmployees());
            } else {
                theBranch.setMin_respondents(MinRespondents.calculateMin(theBranch.getEmployees()));
            }

            List<Area> areas = new ArrayList<>();

            List<Integer> listAreas = BAJ.getAreas();

            for (Integer area : listAreas) {
                Optional<Area> tempArea = areaRepo.findById(area);
                Area theArea = null;
                if (tempArea.isPresent()) {
                    theArea = tempArea.get();
                }
                areas.add(theArea);
            }

            theBranch.setAreas(areas);

            List<Job> jobs = new ArrayList<>();

            List<Integer> listJobs = BAJ.getJobs();

            for (Integer job : listJobs) {
                Optional<Job> tempJob = jobRepo.findById(job);
                Job theJob = null;
                if (tempJob.isPresent()) {
                    theJob = tempJob.get();
                }
                jobs.add(theJob);
            }

            theBranch.setJobs(jobs);

            branchRepository.save(theBranch);

        }

        return "Centros de trabajos actualizados";
    }

    @DeleteMapping("/branches/{branchId}")
    public Branch disableBranch(@RequestHeader String Authorization, @PathVariable int branchId) {
        Optional<Branch> result = branchRepository.findById(branchId);
        Branch theBranch = null;
        if (result.isPresent()) {
            theBranch = result.get();
        } else {
            throw new RuntimeException("Did not find branch id - " + branchId);

        }
        theBranch.setStatus(false);
        branchRepository.save(theBranch);
        return theBranch;
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomNotFoundException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomBadRequestException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
