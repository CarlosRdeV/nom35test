package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Employee;
import com.lofton.nom35.Entity.Question;
import com.lofton.nom35.Entity.Category;
import com.lofton.nom35.Entity.Domain;
import com.lofton.nom35.Entity.EmployeeSurvey;
import com.lofton.nom35.Entity.Response;
import com.lofton.nom35.Entity.Survey;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Repository.EmployeeRepository;
import com.lofton.nom35.Repository.QuestionRepository;
import com.lofton.nom35.Repository.CategoryRepository;
import com.lofton.nom35.Repository.DomainRepository;
import com.lofton.nom35.Repository.EmployeeSurveyRepository;
import com.lofton.nom35.Repository.ResponseRepository;
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.process.reports.Results;
import com.lofton.nom35.templates.BranchResponses;
import com.lofton.nom35.templates.CountResponsesBranch;
import com.lofton.nom35.templates.ResponseComplete;
import com.lofton.nom35.templates.RespuestasTemplate;
import com.lofton.nom35.templates.categoryDomain;
import com.lofton.nom35.templates.dimensionResponses;
import com.lofton.nom35.templates.domainDimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CGCSTF08
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ResponseController {

    private final ResponseRepository responseRepo;

    private final DomainRepository dimensionRepo;

    private final CategoryRepository categoryRepo;

    private final EmployeeRepository employeeRepo;

    private final QuestionRepository questionRepo;

    private final UserRepository userRepo;

    private final EmployeeSurveyRepository empsRepo;

    @Autowired
    public ResponseController(ResponseRepository responseRepo, DomainRepository dimensionRepo, CategoryRepository categoryRepo, EmployeeRepository employeeRepo, QuestionRepository questionRepo, UserRepository userRepo, EmployeeSurveyRepository empsRepo) {
        this.responseRepo = responseRepo;
        this.dimensionRepo = dimensionRepo;
        this.categoryRepo = categoryRepo;
        this.employeeRepo = employeeRepo;
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.empsRepo = empsRepo;
    }

    @GetMapping("/responses")
    public List<Response> getFindAll(@RequestHeader String Authorization) {
        return responseRepo.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/responses/{responseId}")
    public Response getResponse(@RequestHeader String Authorization, @PathVariable int responseId) {

        Optional<Response> result = responseRepo.findById(responseId);

        Response theResponse = null;

        if (result.isPresent()) {
            theResponse = result.get();
        } else {
            throw new CustomNotFoundException("Did not find response id - " + responseId);
        }

        return theResponse;

    }

    @GetMapping("/response/branch/{validator}")
    public List<BranchResponses> getDataSurveyBranch(@RequestHeader String Authorization, @PathVariable String validator) {
        ArrayList<BranchResponses> salida = new ArrayList<>();
        List<Object[]> z = responseRepo.dataSurveyBranchType1(validator);
        String Nececidad = "No es necesaria atención clínica";
        for (Object[] y : z) {
            if ((int) Double.parseDouble(y[6].toString()) == 1) {
                Nececidad = "Se recomienda atención clínica";
            } else {
                Nececidad = "No es necesaria atención clínica";
            }
            salida.add(new BranchResponses(Integer.parseInt(y[0].toString()), y[1].toString(), y[2].toString(), y[3].toString(), Integer.parseInt(y[4].toString()), y[5].toString(), Double.parseDouble(y[6].toString()), Nececidad));
        }

        List<Object[]> x = responseRepo.dataSurveyBranch(validator);
        for (Object[] y : x) {
            String califica = Results.Interpreta(y[7].toString(), (int) Double.parseDouble(y[6].toString()));
            salida.add(new BranchResponses(Integer.parseInt(y[0].toString()), y[1].toString(), y[2].toString(), y[3].toString(), Integer.parseInt(y[4].toString()), y[5].toString(), Double.parseDouble(y[6].toString()), califica));
        }

        return salida;
    }

    @GetMapping("/response/enterprise/{enterpriseId}")
    public Map<String, ArrayList<CountResponsesBranch>> getDataSurveyEnterprise(@RequestHeader String Authorization, @PathVariable int enterpriseId) {
        Map<String, ArrayList<CountResponsesBranch>> salFin = new TreeMap<>();
        ArrayList<CountResponsesBranch> salida = new ArrayList<>();
        List<Object[]> x = responseRepo.enterpriseCountsCenter(enterpriseId);
        List<Object[]> type3 = responseRepo.enterpriseCenterType1(enterpriseId);

        for (Object[] type1 : type3) {
            salida.add(new CountResponsesBranch(type1[0].toString(), type1[1].toString(), Integer.parseInt(type1[2].toString()), Integer.parseInt(type1[3].toString()), Integer.parseInt(type1[3].toString()), Double.parseDouble(type1[5].toString()), "Personas que necesitan atención clínica", type1[7].toString(), Integer.parseInt(type1[8].toString())));
        }
        for (Object[] y : x) {
            String califica = Results.Interpreta(y[6].toString(), (int) Math.round(Double.parseDouble(y[5].toString())));
            salida.add(new CountResponsesBranch(y[0].toString(), y[1].toString(), Integer.parseInt(y[2].toString()), Integer.parseInt(y[3].toString()), Integer.parseInt(y[4].toString()), Double.parseDouble(y[5].toString()), califica, y[7].toString(), Integer.parseInt(y[8].toString())));
        }
        ArrayList<CountResponsesBranch> empresa = new ArrayList<>();
        List<Object[]> z = responseRepo.enterpriseCounts(enterpriseId);
        List<Object[]> type2 = responseRepo.enterpriseType1(enterpriseId);
if (!type2.isEmpty() && type2 !=null ){
        type2.forEach((typez) -> {
            empresa.add(new CountResponsesBranch(typez[0].toString(), typez[1].toString(), Integer.parseInt(typez[2].toString()), Integer.parseInt(typez[3].toString()), Integer.parseInt(typez[4].toString()), Double.parseDouble(typez[5].toString()), "Personas que necesitan atención clínica", typez[7].toString(), Integer.parseInt(typez[8].toString())));
        });

}
        for (Object[] y : z) {
            String califica = Results.Interpreta(y[6].toString(), (int) Math.round(Double.parseDouble(y[5].toString())));
            empresa.add(new CountResponsesBranch(y[0].toString(), y[1].toString(), Integer.parseInt(y[2].toString()), Integer.parseInt(y[3].toString()), Integer.parseInt(y[4].toString()), Double.parseDouble(y[5].toString()), califica, y[7].toString(), Integer.parseInt(y[8].toString())));
        }

        salFin.put("Empresa", empresa);
        salFin.put("CentrosDeTrabajo", salida);

        return salFin;
    }
    
    @GetMapping("/response/client/{validator}")
    public Map<String, ArrayList<CountResponsesBranch>> getDataClient(@RequestHeader String Authorization, @PathVariable String validator) {
        Map<String, ArrayList<CountResponsesBranch>> salFin = new TreeMap<>();
        ArrayList<CountResponsesBranch> salida = new ArrayList<>();
        List<Object[]> x = responseRepo.BranchCounts(validator);
        List<Object[]> type3 = responseRepo.BranchCountsType1(validator);

        for (Object[] type1 : type3) {
            salida.add(new CountResponsesBranch(type1[0].toString(), type1[1].toString(), Integer.parseInt(type1[2].toString()), Integer.parseInt(type1[3].toString()), Integer.parseInt(type1[3].toString()), Double.parseDouble(type1[5].toString()), "Personas que necesitan atención clínica", type1[7].toString(), Integer.parseInt(type1[8].toString())));
        }
        for (Object[] y : x) {
            String califica = Results.Interpreta(y[6].toString(), (int) Math.round(Double.parseDouble(y[5].toString())));
            salida.add(new CountResponsesBranch(y[0].toString(), y[1].toString(), Integer.parseInt(y[2].toString()), Integer.parseInt(y[3].toString()), Integer.parseInt(y[4].toString()), Double.parseDouble(y[5].toString()), califica, y[7].toString(), Integer.parseInt(y[8].toString())));
        }
          salFin.put("CentrosDeTrabajo", salida);

        return salFin;
    }
    

    @GetMapping("/response/employee/{employeeId}/{surveyId}")
    public ArrayList<categoryDomain> getFindEmploye(@RequestHeader String Authorization, @PathVariable int employeeId, @PathVariable int surveyId) {
        List<Response> respuesta = responseRepo.findByEmployeeId(employeeId, surveyId);
        ArrayList<ResponseComplete> nueva = new ArrayList<>();
        Map<Integer, ArrayList<ResponseComplete>> listDimension = new TreeMap<>();
        Map<Integer, Integer> listDimensionTot = new TreeMap<>();
        Map<Integer, Integer> listDomain = new TreeMap<>();
        Map<Integer, Integer> listCategory = new TreeMap<>();
        Map<Integer, String> listDomainName = new TreeMap<>();
        Map<Integer, String> listDomainRange = new TreeMap<>();
        Map<Integer, String> listCategoryName = new TreeMap<>();
        Map<Integer, String> listCategoryRange = new TreeMap<>();
        Map<Integer, String> listDimensionName = new TreeMap<>();
        for (Response resp : respuesta) {

            Domain nDomain = dimensionRepo.findByDimensionId(resp.getQuestion().getDimension().getId());
            Category nCategory = categoryRepo.findByDomainId(nDomain.getId());
            ResponseComplete carga = new ResponseComplete(resp.getQuestion().getDimension().getId(), resp.getQuestion().getPositionQuestion(), resp.getValue(), resp.getQuestion().getText());
            nueva.add(carga);
            if (listDimension.get(resp.getQuestion().getDimension().getId()) == null) {
                ArrayList<ResponseComplete> Itera = new ArrayList<>();
                Itera.add(carga);
                listDimension.put(resp.getQuestion().getDimension().getId(), Itera);
                listDimensionTot.put(resp.getQuestion().getDimension().getId(), Integer.parseInt(resp.getValue()));
                listDimensionName.put(resp.getQuestion().getDimension().getId(), resp.getQuestion().getDimension().getName());
                listDomain.put(resp.getQuestion().getDimension().getId(), nDomain.getId());
                listDomainName.put(nDomain.getId(), nDomain.getName());
                listDomainRange.put(nDomain.getId(), nDomain.getRangos());
                listCategory.put(resp.getQuestion().getDimension().getId(), nCategory.getId());
                listCategoryName.put(nCategory.getId(), nCategory.getName());
                listCategoryRange.put(nCategory.getId(), nCategory.getRangos());

            } else {
                ArrayList<ResponseComplete> Itera = listDimension.get(resp.getQuestion().getDimension().getId());
                Itera.add(carga);
                listDimension.put(resp.getQuestion().getDimension().getId(), Itera);
                int tot = listDimensionTot.get(resp.getQuestion().getDimension().getId());
                listDimensionTot.put(resp.getQuestion().getDimension().getId(), tot + Integer.parseInt(resp.getValue()));

            }

        }

        ArrayList<domainDimension> domains = new ArrayList<>();
        Map<Integer, ArrayList<dimensionResponses>> listDomainArray = new TreeMap<>();
        Map<Integer, Integer> listCategoryDomain = new TreeMap<>();
        Map<Integer, Integer> listDomainTot = new TreeMap<>();
        for (int x : listDimension.keySet()) {
            dimensionResponses nv1 = new dimensionResponses(listCategory.get(x), listDomain.get(x), x, listDimensionName.get(x), listDimensionTot.get(x), listDimension.get(x));
            if (listDomainArray.get(listDomain.get(x)) == null) {
                ArrayList<dimensionResponses> itera = new ArrayList<>();
                itera.add(nv1);
                listDomainArray.put(listDomain.get(x), itera);
                listCategoryDomain.put(listDomain.get(x), listCategory.get(x));
                listDomainTot.put(listDomain.get(x), listDimensionTot.get(x));
            } else {
                ArrayList<dimensionResponses> itera = listDomainArray.get(listDomain.get(x));
                itera.add(nv1);
                listDomainArray.put(listDomain.get(x), itera);
                int tot = listDomainTot.get(listDomain.get(x));
                listDomainTot.put(listDomain.get(x), tot + listDimensionTot.get(x));
            }

        }
        ArrayList<categoryDomain> categories = new ArrayList<>();
        Map<Integer, Integer> listCategoryTot = new TreeMap<>();
        Map<Integer, ArrayList<domainDimension>> listCategoryArray = new TreeMap<>();
        for (int x : listDomainName.keySet()) {
            String[] env = listDomainRange.get(x).split(";");
            String Fenv = "10@30@80@120";
            if (surveyId == 2) {
                Fenv = env[0];
            } else {
                Fenv = env[1];
            }
            String inter = Results.Interpreta(Fenv = env[1], listDomainTot.get(x));
            domainDimension nvDomain = new domainDimension(x, listCategoryDomain.get(x), listDomainName.get(x), listDomainTot.get(x), listDomainArray.get(x), inter);
            if (listCategoryArray.get(listCategoryDomain.get(x)) == null) {
                ArrayList<domainDimension> itera = new ArrayList<>();
                itera.add(nvDomain);
                listCategoryArray.put(listCategoryDomain.get(x), itera);

                listCategoryTot.put(listCategoryDomain.get(x), listDomainTot.get(x));
            } else {
                ArrayList<domainDimension> itera = listCategoryArray.get(listCategoryDomain.get(x));
                itera.add(nvDomain);
                listCategoryArray.put(listCategoryDomain.get(x), itera);
                int tot = listCategoryTot.get(listCategoryDomain.get(x));
                listCategoryTot.put(listCategoryDomain.get(x), tot + listDomainTot.get(x));
            }

            domains.add(nvDomain);
        }
        for (int x : listCategoryName.keySet()) {
            String[] env = listCategoryRange.get(x).split(";");
            String Fenv = "10@30@80@120";
            if (surveyId == 2) {
                Fenv = env[0];
            } else {
                Fenv = env[1];
            }
            String inter = Results.Interpreta(Fenv = env[1], listCategoryTot.get(x));
            categoryDomain cat = new categoryDomain(x, listCategoryName.get(x), listCategoryTot.get(x), listCategoryArray.get(x), inter);
            categories.add(cat);
        }

        return categories; //responseRepo.findByEmployeeId(employeeId);
    }

    @GetMapping("/response/branch/survey/{validator}/{surveyId}")
    public ArrayList<categoryDomain> getFindResponseBranch(@RequestHeader String Authorization, @PathVariable String validator, @PathVariable int surveyId) {
        List<Response> respuesta = responseRepo.branchTotals(validator, surveyId);
        ArrayList<ResponseComplete> nueva = new ArrayList<>();
        Map<Integer, ArrayList<ResponseComplete>> listDimension = new TreeMap<>();
        Map<Integer, Double> listDimensionTot = new TreeMap<>();
        Map<Integer, Integer> listDomain = new TreeMap<>();
        Map<Integer, Integer> listCategory = new TreeMap<>();
        Map<Integer, String> listDomainName = new TreeMap<>();
        Map<Integer, String> listDomainRange = new TreeMap<>();
        Map<Integer, String> listCategoryName = new TreeMap<>();
        Map<Integer, String> listCategoryRange = new TreeMap<>();
        Map<Integer, String> listDimensionName = new TreeMap<>();
        for (Response resp : respuesta) {

            Domain nDomain = dimensionRepo.findByDimensionId(resp.getQuestion().getDimension().getId());
            Category nCategory = categoryRepo.findByDomainId(nDomain.getId());
            ResponseComplete carga = new ResponseComplete(resp.getQuestion().getDimension().getId(), resp.getQuestion().getPositionQuestion(), resp.getValue(), resp.getQuestion().getText());
            nueva.add(carga);
            if (listDimension.get(resp.getQuestion().getDimension().getId()) == null) {
                ArrayList<ResponseComplete> Itera = new ArrayList<>();
                Itera.add(carga);
                listDimension.put(resp.getQuestion().getDimension().getId(), Itera);
                listDimensionTot.put(resp.getQuestion().getDimension().getId(), Double.parseDouble(resp.getValue()));
                listDimensionName.put(resp.getQuestion().getDimension().getId(), resp.getQuestion().getDimension().getName());
                listDomain.put(resp.getQuestion().getDimension().getId(), nDomain.getId());
                listDomainName.put(nDomain.getId(), nDomain.getName());
                listDomainRange.put(nDomain.getId(), nDomain.getRangos());
                listCategory.put(resp.getQuestion().getDimension().getId(), nCategory.getId());
                listCategoryName.put(nCategory.getId(), nCategory.getName());
                listCategoryRange.put(nCategory.getId(), nCategory.getRangos());

            } else {
                ArrayList<ResponseComplete> Itera = listDimension.get(resp.getQuestion().getDimension().getId());
                Itera.add(carga);
                listDimension.put(resp.getQuestion().getDimension().getId(), Itera);
                Double tot = listDimensionTot.get(resp.getQuestion().getDimension().getId());
                listDimensionTot.put(resp.getQuestion().getDimension().getId(), tot + Double.parseDouble(resp.getValue()));

            }

        }

        ArrayList<domainDimension> domains = new ArrayList<>();
        Map<Integer, ArrayList<dimensionResponses>> listDomainArray = new TreeMap<>();
        Map<Integer, Integer> listCategoryDomain = new TreeMap<>();
        Map<Integer, Double> listDomainTot = new TreeMap<>();
        for (int x : listDimension.keySet()) {
            dimensionResponses nv1 = new dimensionResponses(listCategory.get(x), listDomain.get(x), x, listDimensionName.get(x), (int) Math.round(listDimensionTot.get(x)), listDimension.get(x));
            if (listDomainArray.get(listDomain.get(x)) == null) {
                ArrayList<dimensionResponses> itera = new ArrayList<>();
                itera.add(nv1);
                listDomainArray.put(listDomain.get(x), itera);
                listCategoryDomain.put(listDomain.get(x), listCategory.get(x));
                listDomainTot.put(listDomain.get(x), listDimensionTot.get(x));
            } else {
                ArrayList<dimensionResponses> itera = listDomainArray.get(listDomain.get(x));
                itera.add(nv1);
                listDomainArray.put(listDomain.get(x), itera);
                double tot = listDomainTot.get(listDomain.get(x));
                listDomainTot.put(listDomain.get(x), tot + listDimensionTot.get(x));
            }

        }
        ArrayList<categoryDomain> categories = new ArrayList<>();
        Map<Integer, Double> listCategoryTot = new TreeMap<>();
        Map<Integer, ArrayList<domainDimension>> listCategoryArray = new TreeMap<>();
        for (int x : listDomainName.keySet()) {
            String[] env = listDomainRange.get(x).split(";");
            String Fenv = "10@30@80@120";
            if (surveyId == 2) {
                Fenv = env[0];
            } else {
                Fenv = env[1];
            }
            String inter = Results.Interpreta(Fenv = env[1], (int) Math.round(listDomainTot.get(x)));
            domainDimension nvDomain = new domainDimension(x, listCategoryDomain.get(x), listDomainName.get(x), (int) Math.round(listDomainTot.get(x)), listDomainArray.get(x), inter);
            if (listCategoryArray.get(listCategoryDomain.get(x)) == null) {
                ArrayList<domainDimension> itera = new ArrayList<>();
                itera.add(nvDomain);
                listCategoryArray.put(listCategoryDomain.get(x), itera);

                listCategoryTot.put(listCategoryDomain.get(x), listDomainTot.get(x));
            } else {
                ArrayList<domainDimension> itera = listCategoryArray.get(listCategoryDomain.get(x));
                itera.add(nvDomain);
                listCategoryArray.put(listCategoryDomain.get(x), itera);
                double tot = listCategoryTot.get(listCategoryDomain.get(x));
                listCategoryTot.put(listCategoryDomain.get(x), tot + listDomainTot.get(x));
            }

            domains.add(nvDomain);
        }
        for (int x : listCategoryName.keySet()) {
            String[] env = listCategoryRange.get(x).split(";");
            String Fenv = "10@30@80@120";
            if (surveyId == 2) {
                Fenv = env[0];
            } else {
                Fenv = env[1];
            }
            String inter = Results.Interpreta(Fenv = env[1], (int) Math.round(listCategoryTot.get(x)));
            categoryDomain cat = new categoryDomain(x, listCategoryName.get(x), (int) Math.round(listCategoryTot.get(x)), listCategoryArray.get(x), inter);
            categories.add(cat);
        }

        return categories; //responseRepo.findByEmployeeId(employeeId);
    }

    @GetMapping("/response/enterprise/survey/{enterpriseId}/{surveyId}")
    public ArrayList<categoryDomain> getFindResponseEnterprise(@RequestHeader String Authorization, @PathVariable int enterpriseId, @PathVariable int surveyId) {
        List<Response> respuesta = responseRepo.EnterpriseTotals(enterpriseId, surveyId);
        ArrayList<ResponseComplete> nueva = new ArrayList<>();
        Map<Integer, ArrayList<ResponseComplete>> listDimension = new TreeMap<>();
        Map<Integer, Double> listDimensionTot = new TreeMap<>();
        Map<Integer, Integer> listDomain = new TreeMap<>();
        Map<Integer, Integer> listCategory = new TreeMap<>();
        Map<Integer, String> listDomainName = new TreeMap<>();
        Map<Integer, String> listDomainRange = new TreeMap<>();
        Map<Integer, String> listCategoryName = new TreeMap<>();
        Map<Integer, String> listCategoryRange = new TreeMap<>();
        Map<Integer, String> listDimensionName = new TreeMap<>();
        for (Response resp : respuesta) {

            Domain nDomain = dimensionRepo.findByDimensionId(resp.getQuestion().getDimension().getId());
            Category nCategory = categoryRepo.findByDomainId(nDomain.getId());
            ResponseComplete carga = new ResponseComplete(resp.getQuestion().getDimension().getId(), resp.getQuestion().getPositionQuestion(), resp.getValue(), resp.getQuestion().getText());
            nueva.add(carga);
            if (listDimension.get(resp.getQuestion().getDimension().getId()) == null) {
                ArrayList<ResponseComplete> Itera = new ArrayList<>();
                Itera.add(carga);
                listDimension.put(resp.getQuestion().getDimension().getId(), Itera);
                listDimensionTot.put(resp.getQuestion().getDimension().getId(), Double.parseDouble(resp.getValue()));
                listDimensionName.put(resp.getQuestion().getDimension().getId(), resp.getQuestion().getDimension().getName());
                listDomain.put(resp.getQuestion().getDimension().getId(), nDomain.getId());
                listDomainName.put(nDomain.getId(), nDomain.getName());
                listDomainRange.put(nDomain.getId(), nDomain.getRangos());
                listCategory.put(resp.getQuestion().getDimension().getId(), nCategory.getId());
                listCategoryName.put(nCategory.getId(), nCategory.getName());
                listCategoryRange.put(nCategory.getId(), nCategory.getRangos());

            } else {
                ArrayList<ResponseComplete> Itera = listDimension.get(resp.getQuestion().getDimension().getId());
                Itera.add(carga);
                listDimension.put(resp.getQuestion().getDimension().getId(), Itera);
                Double tot = listDimensionTot.get(resp.getQuestion().getDimension().getId());
                listDimensionTot.put(resp.getQuestion().getDimension().getId(), tot + Double.parseDouble(resp.getValue()));

            }

        }

        ArrayList<domainDimension> domains = new ArrayList<>();
        Map<Integer, ArrayList<dimensionResponses>> listDomainArray = new TreeMap<>();
        Map<Integer, Integer> listCategoryDomain = new TreeMap<>();
        Map<Integer, Double> listDomainTot = new TreeMap<>();
        for (int x : listDimension.keySet()) {
            dimensionResponses nv1 = new dimensionResponses(listCategory.get(x), listDomain.get(x), x, listDimensionName.get(x), (int) Math.round(listDimensionTot.get(x)), listDimension.get(x));
            if (listDomainArray.get(listDomain.get(x)) == null) {
                ArrayList<dimensionResponses> itera = new ArrayList<>();
                itera.add(nv1);
                listDomainArray.put(listDomain.get(x), itera);
                listCategoryDomain.put(listDomain.get(x), listCategory.get(x));
                listDomainTot.put(listDomain.get(x), listDimensionTot.get(x));
            } else {
                ArrayList<dimensionResponses> itera = listDomainArray.get(listDomain.get(x));
                itera.add(nv1);
                listDomainArray.put(listDomain.get(x), itera);
                double tot = listDomainTot.get(listDomain.get(x));
                listDomainTot.put(listDomain.get(x), tot + listDimensionTot.get(x));
            }

        }
        ArrayList<categoryDomain> categories = new ArrayList<>();
        Map<Integer, Double> listCategoryTot = new TreeMap<>();
        Map<Integer, ArrayList<domainDimension>> listCategoryArray = new TreeMap<>();
        for (int x : listDomainName.keySet()) {
            String[] env = listDomainRange.get(x).split(";");
            String Fenv = "10@30@80@120";
            if (surveyId == 2) {
                Fenv = env[0];
            } else {
                Fenv = env[1];
            }
            String inter = Results.Interpreta(Fenv = env[1], (int) Math.round(listDomainTot.get(x)));
            domainDimension nvDomain = new domainDimension(x, listCategoryDomain.get(x), listDomainName.get(x), (int) Math.round(listDomainTot.get(x)), listDomainArray.get(x), inter);
            if (listCategoryArray.get(listCategoryDomain.get(x)) == null) {
                ArrayList<domainDimension> itera = new ArrayList<>();
                itera.add(nvDomain);
                listCategoryArray.put(listCategoryDomain.get(x), itera);

                listCategoryTot.put(listCategoryDomain.get(x), listDomainTot.get(x));
            } else {
                ArrayList<domainDimension> itera = listCategoryArray.get(listCategoryDomain.get(x));
                itera.add(nvDomain);
                listCategoryArray.put(listCategoryDomain.get(x), itera);
                double tot = listCategoryTot.get(listCategoryDomain.get(x));
                listCategoryTot.put(listCategoryDomain.get(x), tot + listDomainTot.get(x));
            }

            domains.add(nvDomain);
        }
        for (int x : listCategoryName.keySet()) {
            String[] env = listCategoryRange.get(x).split(";");
            String Fenv = "10@30@80@120";
            if (surveyId == 2) {
                Fenv = env[0];
            } else {
                Fenv = env[1];
            }
            String inter = Results.Interpreta(Fenv = env[1], (int) Math.round(listCategoryTot.get(x)));
            categoryDomain cat = new categoryDomain(x, listCategoryName.get(x), (int) Math.round(listCategoryTot.get(x)), listCategoryArray.get(x), inter);
            categories.add(cat);
        }

        return categories; //responseRepo.findByEmployeeId(employeeId);
    }

    @PostMapping("/responses")
    public void addResponseList(@RequestHeader String Authorization, @RequestBody List<RespuestasTemplate> respuestas) {

        Survey theSurvey = new Survey();

        User tempUser = userRepo.findByUsername(respuestas.get(0).getUsername());

        Optional<Employee> resultEmployee = employeeRepo.findByUser(tempUser);

        Employee theEmployee = null;

        if (resultEmployee.isPresent()) {
            theEmployee = resultEmployee.get();
        } else {
            throw new CustomNotFoundException("Did not find employee");
        }

        for (RespuestasTemplate respuesta : respuestas) {
            //traer pregunta
            Optional<Question> resultQuestion = questionRepo.findById(respuesta.getIdQuestion());

            Question theQuestion = null;

            if (resultQuestion.isPresent()) {
                theQuestion = resultQuestion.get();
            } else {
                throw new CustomNotFoundException("Did not find question id - " + respuesta.getIdQuestion());
            }

            //asignar parametros 
            Response tempResponse = new Response();
            tempResponse.setId(0);
            tempResponse.setEmployee(theEmployee);
            tempResponse.setQuestion(theQuestion);
            tempResponse.setValue(respuesta.getResponseValue().toString());
            //guardar
            responseRepo.save(tempResponse);
            //traemos la encuesta
            theSurvey = theQuestion.getSurvey();
        }

        //buscar el empleado y la encuesta, cambiar status a false
        Optional<EmployeeSurvey> ems = empsRepo.findByEmployeeAndSurvey(theEmployee, theSurvey);

        EmployeeSurvey theEMS = null;

        if (ems.isPresent()) {
            theEMS = ems.get();
            theEMS.setEnable(Boolean.FALSE);
            empsRepo.save(theEMS);
        }
    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/responses")
    public Response updateResponse(@RequestHeader String Authorization, @RequestBody Response theResponse) {
        responseRepo.save(theResponse);
        return theResponse;
    }

}
