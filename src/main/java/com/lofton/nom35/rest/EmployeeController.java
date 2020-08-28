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
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.Repository.WorkdayRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.passay.AllowedCharacterRule.ERROR_CODE;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
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
public class EmployeeController {
private String[] columns = {"Nombre", "Apellido Paterno", "Apellido Materno", "Edad", "Sexo", "Estado Civil", "Grado Académico", "Puesto", "Área", "Jornada", "Años laborando", "Usuario", "Contraseña"};

    private String[] sexos = {"Masculino", "Femenino"};

    private String[] estadoCivil = {"Casado/a", "Soltero/a", "Unión libre", "Divorciado/a", "Viudo/a"};

    private String[] edad = {"15 - 19", "20 - 24", "25 - 29", "30 - 34", "35 - 39", "40 - 44", "45 - 49", "50 - 54", "55 - 59", "60 - 64", "65 - 69", "70 o más"};

    private String[] estudios = {"Primaria", "Secundaria", "Preparatoria o Bachillerato", "Técnico Superior", "Licenciatura", "Maestría", "Doctorado"};

    private String[] jornadas = {"Diurno", "Mixto", "Nocturno"};

    private String[] puestoStatic = {};

    private String[] areaStatic = {};

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

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestHeader String Authorization, @RequestBody Employee theEmployee) {

        Optional<Employee> tempEmployee = employeeRepository.findById(theEmployee.getId());
        Employee actualEmployee = null;
        if (tempEmployee.isPresent()) {
            actualEmployee = tempEmployee.get();
        } else {
            throw new CustomNotFoundException("El empleado no se encuentra");
        }
        theEmployee.setResponses(actualEmployee.getResponses());
        theEmployee.setEmployeeSurveys(actualEmployee.getEmployeeSurveys());
        theEmployee.setBranch(actualEmployee.getBranch());
        theEmployee.setUser(actualEmployee.getUser());
        theEmployee.setStatus(actualEmployee.getStatus());

        employeeRepository.save(theEmployee);
        return theEmployee;
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

    @GetMapping("/downxl/{validator}")
    public void getExcel(@RequestHeader String Authorization, HttpServletResponse response, @PathVariable String validator) throws IOException {

        Optional<Branch> branch = branchRepository.findByValidator(validator);
        Branch theBranch = null;
        if (branch.isPresent()) {
            theBranch = branch.get();
        } else {
            throw new CustomBadRequestException("No se encontro un centro de trabajo para ese validador");
        }
        int filas = theBranch.getEmployees() + 1;

        OutputStream outStream = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("layoutCT");
        XSSFSheet hidden = workbook.createSheet("hidden");

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);

        // con patron solido del color indicado
        style.setBorderBottom(BorderStyle.THIN);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFRow row = hidden.createRow(0);
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(style);
            //sheet.autoSizeColumn(i);
        }

        sheet.addValidationData(getListaVar(filas, 3, sheet, edad));
        sheet.addValidationData(getListaVar(filas, 4, sheet, sexos));
        sheet.addValidationData(getListaVar(filas, 5, sheet, estadoCivil));
        sheet.addValidationData(getListaVar(filas, 6, sheet, estudios));
        sheet.addValidationData(getLista(theBranch, "puesto", filas, 7, sheet, hidden, row));
        sheet.addValidationData(getLista(theBranch, "area", filas, 8, sheet, hidden, row));
        sheet.addValidationData(getListaVar(filas, 9, sheet, jornadas));

        for (int i = 1; i < filas; i++) {
            XSSFRow row1 = sheet.createRow(i);
            Cell cell0 = row1.createCell(0);
            Cell cell1 = row1.createCell(1);
            Cell cell2 = row1.createCell(2);

            Cell cell3 = row1.createCell(3);
            Cell cell4 = row1.createCell(4);
            Cell cell5 = row1.createCell(5);
            Cell cell6 = row1.createCell(6);
            Cell cell7 = row1.createCell(7);
            Cell cell8 = row1.createCell(8);
            Cell cell9 = row1.createCell(9);
            Cell cell10 = row1.createCell(10);
            Cell cell11 = row1.createCell(11);
            Cell cell12 = row1.createCell(12);

            cell0.setCellValue("");
            cell1.setCellValue("");
            cell2.setCellValue("");
            cell3.setCellValue(edad[0]);    //estaticos
            cell4.setCellValue(sexos[0]);
            cell5.setCellValue(estadoCivil[0]);
            cell6.setCellValue(estudios[0]);
            cell7.setCellValue(puestoStatic[0]); //dinamicos
            cell8.setCellValue(areaStatic[0]);
            cell9.setCellValue(jornadas[0]);
            cell10.setCellValue("0");
            cell11.setCellValue("");
            cell12.setCellValue(generatePassayPassword());

        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        try {
            workbook.setSheetHidden(1, true);
            workbook.write(outByteStream);
        } catch (IOException ex) {
            //   Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] outArray = outByteStream.toByteArray();

        setHeadersArchivoAplication(response, "ms-excel", outArray, "layoutCT.xlsx");
        outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();

    }

    public static void setHeadersArchivoAplication(HttpServletResponse response, String extension, byte[] outArray, String name) {
        response.setContentType(String.format("application/%s", extension));
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s", name));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    //lista pequenia 
    private static DataValidation getListaVar(int filas, int columna, XSSFSheet sheet, String[] arreglo) {

        CellRangeAddressList addressList = new CellRangeAddressList(1, filas, columna, columna);
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = null;
        dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(arreglo);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        return validation;
    }

    private DataValidation getLista(Branch theBranch, String campo, int filas, int columna, XSSFSheet sheet, XSSFSheet hidden, XSSFRow row) {

        CellRangeAddressList addressList = new CellRangeAddressList(1, filas, columna, columna);
        String[] lista = null;

        switch (campo) {
            case "area":
                List<Area> areas = theBranch.getAreas();
                lista = new String[areas.size()];
                int cont = 0;
                for (Area n : areas) {
                    lista[cont] = String.format("%s", n.getAreaname());
                    cont++;
                }
                areaStatic = lista;
                for (int i = 0, length = lista.length; i < length; i++) {
                    String name = lista[i];
                    int compara = hidden.getLastRowNum();

                    if (compara == 0 && i == 0) {
                        row = hidden.getRow(i);
                    } else if (compara < i) {
                        row = hidden.createRow(i);
                    } else {
                        row = hidden.getRow(i);
                    }
                    XSSFCell cell = row.createCell(1);
                    cell.setCellValue(name);
                }
                break;
            case "puesto":
                List<Job> puestos = theBranch.getJobs();
                lista = new String[puestos.size()];
                int cont2 = 0;
                for (Job a : puestos) {
                    lista[cont2] = String.format("%s", a.getJobname());
                    cont2++;
                }
                puestoStatic = lista;
                for (int i = 0, length = lista.length; i < length; i++) {
                    String name = lista[i];
                    int compara = hidden.getLastRowNum();

                    if (compara == 0 && i == 0) {
                        row = hidden.getRow(i);
                    } else if (compara < i) {
                        row = hidden.createRow(i);
                    } else {
                        row = hidden.getRow(i);
                    }
                    XSSFCell cell = row.createCell(0);
                    cell.setCellValue(name);
                }
                break;

            default:

                break;
        }
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = null;
        switch (campo) {
            case "area":
                dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint("hidden!$B$1:$B$" + lista.length);
                break;
            case "puesto":
                dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint("hidden!$A$1:$A$" + lista.length);
                break;

            default:

                break;
        }
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        return validation;
    }

    //generate password
    public static String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!#$&*";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

    //add exception handler 
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomNotFoundException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //add exception handler 
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomBadRequestException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
