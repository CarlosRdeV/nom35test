/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Area;
import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Entity.Employee;
import com.lofton.nom35.Entity.EmployeeSurvey;
import com.lofton.nom35.Entity.Enterprise;
import com.lofton.nom35.Entity.Job;
import com.lofton.nom35.Entity.Survey;
import com.lofton.nom35.Entity.Workday;

import com.lofton.nom35.Repository.AreaRepository;
import com.lofton.nom35.Repository.BranchRepository;
import com.lofton.nom35.Repository.EmployeeRepository;
import com.lofton.nom35.Repository.EmployeeSurveyRepository;
import com.lofton.nom35.Repository.EnterpriseRepository;
import com.lofton.nom35.Repository.JobRepository;
import com.lofton.nom35.Repository.WorkdayRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private BranchRepository branchRepository;
    private AreaRepository areaRepository;
    private JobRepository jobRepository;
    private WorkdayRepository workdayRepository;
    private EnterpriseRepository enterpriseRepo;
    private EmployeeSurveyRepository emsRepo;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, BranchRepository branchRepository, AreaRepository areaRepository, JobRepository jobRepository, WorkdayRepository workdayRepository, EnterpriseRepository enterpriseRepo, EmployeeSurveyRepository emsRepo) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.areaRepository = areaRepository;
        this.jobRepository = jobRepository;
        this.workdayRepository = workdayRepository;
        this.enterpriseRepo = enterpriseRepo;
        this.emsRepo = emsRepo;
    }

    @GetMapping("/employees")
    public List<Employee> getFindAll(@RequestHeader String Authorization) {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/branches/{branchValidator}")
    public List<Employee> getFindAllByBranch(@RequestHeader String Authorization, @PathVariable String branchValidator) {

        //traer branch
        Optional<Branch> tempBranch = branchRepository.findByValidator(branchValidator);
        Branch theBranch = null;

        if (tempBranch.isPresent()) {
            theBranch = tempBranch.get();
        } else {
            throw new CustomNotFoundException("Did not find branch validator - " + branchValidator);
        }

        List<Employee> employeeList = employeeRepository.findByBranch(theBranch);

        return employeeList;
    }

    @GetMapping("/employees/enterprise/{rfc}")
    public List<Employee> getFindAllByEnterprise(@RequestHeader String Authorization, @PathVariable String rfc) {

        //traer branch
        Enterprise tempEnterprise = enterpriseRepo.findByRfc(rfc);

        List<Branch> branches = tempEnterprise.getBranches();

        List<Employee> empleadosEnterprise = new ArrayList<>();

        for (int i = 0; i < branches.size(); i++) {
            List<Employee> empleadosBranch = employeeRepository.findByBranch(branches.get(i));
            empleadosEnterprise.addAll(empleadosBranch);
        }

        System.out.println(empleadosEnterprise);
//        List<Employee> employeeEnterprise = new ListBinding<Employee>

        //   List<Employee> employeeList = employeeRepository.findByBranch(theBranch);
        return empleadosEnterprise;
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployees(@RequestHeader String Authorization, @PathVariable int employeeId) {

        Optional<Employee> result = employeeRepository.findById(employeeId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new CustomNotFoundException("Did not find area id - " + employeeId);
        }

        theEmployee.getBranch().setAreas(null);
        theEmployee.getBranch().setJobs(null);
        // theEmployee.getBranch().setSurveys(null);

        return theEmployee;

    }

    @PostMapping("/employees/{validator}/{areaId}/{jobId}/{workdayId}")
    public Employee addEmployee(
            @RequestHeader String Authorization,
            @RequestBody Employee theEmployee,
            @PathVariable String validator,
            @PathVariable int areaId,
            @PathVariable int jobId,
            @PathVariable int workdayId) {

        theEmployee.setId(0);
        theEmployee.setStatus(Boolean.TRUE);

        // asignacion de branch al empleado
        Optional<Branch> theBranch = branchRepository.findByValidator(validator);
        Branch tempBranch = null;
        if (theBranch.isPresent()) {
            tempBranch = theBranch.get();
            theEmployee.setBranch(tempBranch);

        } else {
            throw new RuntimeException("Did not find branch validator - " + validator);
        }

        // asignacion de area al empleado
        Optional<Area> theArea = areaRepository.findById(areaId);
        Area tempArea = null;
        if (theArea.isPresent()) {
            tempArea = theArea.get();

            //usamos el branch para validar que exista la informacion que el usuario capturo
            //traenmos la lista de las areas disponibles
            List<Area> areaEnBranch = tempBranch.getAreas();
            //si existe dentro de la lista  
            if (!areaEnBranch.contains(tempArea)) {
                throw new RuntimeException("Area not found in this branch");
            }

            theEmployee.setArea(tempArea);
        } else {
            throw new RuntimeException("Did not find area id - " + areaId);
        }

        // asignacion de job al empleado
        Optional<Job> theJob = jobRepository.findById(jobId);
        Job tempJob = null;
        if (theJob.isPresent()) {
            tempJob = theJob.get();

            //usamos el branch para validar que exista la informacion que el usuario capturo
            //traenmos la lista de las areas disponibles
            List<Job> jobEnBranch = tempBranch.getJobs();
            //si existe dentro de la lista  
            if (!jobEnBranch.contains(tempJob)) {
                throw new RuntimeException("Job not found in this branch");
            }

            theEmployee.setJob(tempJob);
        } else {
            throw new RuntimeException("Did not find job id - " + jobId);
        }

        // asignacion de workday al empleado
        Optional<Workday> theWorkday = workdayRepository.findById(workdayId);
        Workday tempWorkday = null;
        if (theWorkday.isPresent()) {
            tempWorkday = theWorkday.get();
            theEmployee.setWorkday(tempWorkday);
        } else {
            throw new RuntimeException("Did not find work id - " + workdayId);
        }

        employeeRepository.save(theEmployee);

        Employee tempEmployee = employeeRepository.findByEmployeeNameAndBranchAndWorkYears(theEmployee.getEmployeeName(), tempBranch, theEmployee.getWorkYears());

        //traemos las encuestas del branch
        List<Survey> surveys = tempBranch.getSurveys();

        System.out.println("Estas son las survey del branch");
        System.out.println(surveys);

        for (Survey survey : surveys) {
            EmployeeSurvey ems = new EmployeeSurvey(0, tempEmployee, survey, Boolean.TRUE);
            emsRepo.save(ems);
        }
        return tempEmployee;

    }

    // add mapping for DELETE /users/{userId} - disable existing user
    @DeleteMapping("/employees/{employeeId}")
    public Employee disableEmployee(@RequestHeader String Authorization, @PathVariable int employeeId) {

        Optional<Employee> tempEmployee = employeeRepository.findById(employeeId);

        Employee theEmployee = null;

        if (tempEmployee.isPresent()) {
            theEmployee = tempEmployee.get();
        } else {
            throw new RuntimeException("Job id not found - " + employeeId);
        }

        theEmployee.setStatus(false);

        employeeRepository.save(theEmployee);

        return theEmployee;
    }

}
