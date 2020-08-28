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
import com.lofton.nom35.Entity.Job;
import com.lofton.nom35.Entity.Role;
import com.lofton.nom35.Entity.Survey;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Entity.Workday;
import com.lofton.nom35.Repository.AreaRepository;
import com.lofton.nom35.Repository.BranchRepository;
import com.lofton.nom35.Repository.EmployeeRepository;
import com.lofton.nom35.Repository.EmployeeSurveyRepository;
import com.lofton.nom35.Repository.JobRepository;
import com.lofton.nom35.Repository.RoleRepository;
import com.lofton.nom35.Repository.SurveyRepository;
import com.lofton.nom35.Repository.TokenRepository;
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.Repository.WorkdayRepository;
import com.lofton.nom35.service.JwtUserDetailsService;
import com.lofton.nom35.service.UploadService;
import com.lofton.nom35.springsecurity.config.JwtTokenUtil;
import com.lofton.nom35.templates.EmpleadoLayout;
import com.lofton.nom35.templates.UserPassword;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UploadService uploadService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final TokenRepository tokenRepo;

    private final PasswordEncoder bcryptEncoder;

    private final RoleRepository roleRepo;

    private final AreaRepository areaRepo;

    private final JobRepository jobRepo;

    private final WorkdayRepository workRepo;

    private final BranchRepository branchRepo;

    private final UserRepository userRepo;

    private final SurveyRepository surveyRepo;

    private final EmployeeRepository employeeRepo;

    private final EmployeeSurveyRepository emsRepo;

    @Autowired
    public UserController(UploadService uploadService, UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, TokenRepository tokenRepo, PasswordEncoder bcryptEncoder, RoleRepository roleRepo, AreaRepository areaRepo, JobRepository jobRepo, WorkdayRepository workRepo, BranchRepository branchRepo, UserRepository userRepo, SurveyRepository surveyRepo, EmployeeRepository employeeRepo, EmployeeSurveyRepository emsRepo) {
        this.uploadService = uploadService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.tokenRepo = tokenRepo;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepo = roleRepo;
        this.areaRepo = areaRepo;
        this.jobRepo = jobRepo;
        this.workRepo = workRepo;
        this.branchRepo = branchRepo;
        this.userRepo = userRepo;
        this.surveyRepo = surveyRepo;
        this.employeeRepo = employeeRepo;
        this.emsRepo = emsRepo;
    }

    // add mapping for GET /users - return list of all users
    @GetMapping("/users")
    public List<User> getFindAllUsers(@RequestHeader String Authorization) {
        List<User> all = userRepository.findUsers();
        //remover el primero
        all.remove(0);
        return all;
    }

    @GetMapping("/users/employees")
    public List<User> getFindAllEmployeeUsers(@RequestHeader String Authorization) {
        return userRepository.findEmployees();
    }

    // add mapping for GET /users/{userId} - return user if exists
    @GetMapping("/users/{userId}")
    public User getUser(@RequestHeader String Authorization, @PathVariable int userId) {

        if (userId == 1) {
            return new User();
        }
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        return theUser;

    }

    // // add mapping for POST /users - add new user 
    @PutMapping("/users")
    public User updateUser(@RequestHeader String Authorization, @RequestBody UserPassword theUser) throws Exception {

        //solicitar el usuario
        User tempUser = userRepository.findByUsername(theUser.getUsername());

        if (tempUser.getId() == 1) {
            return new User();
        }

        //solicitar password anterior para modificar contrasena
        java.util.regex.Pattern pat = java.util.regex.Pattern.compile("^[a-zA-Z0-9!@#$%^&*()-=,.:\\{}<>\\\"']*$");
        Matcher mat = pat.matcher(theUser.getNewPassword());

        if (!mat.matches()) {
            throw new RuntimeException("La contrasena contiene caracteres invalidos");
        }

        if (theUser.getNewPassword().length() < 4 || theUser.getNewPassword().length() > 16) {
            throw new RuntimeException("La contrasena debe estar entre 4 y 16 caracteres");
        }

        tempUser.setPassword(bcryptEncoder.encode(theUser.getNewPassword()));
        userRepository.save(tempUser);

        return tempUser;

    }

    @PutMapping("/users/roles/{userId}/{roleId}")
    public User addRole(
            @RequestHeader String Authorization,
            @PathVariable int userId,
            @PathVariable int roleId) {

        //traer el usuario
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
            if (theUser.getId() == 1) {
                return new User();
            }
        } else {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        //traer el role
        Optional<Role> resultRole = roleRepo.findById(roleId);

        Role theRole = null;

        if (resultRole.isPresent()) {
            theRole = resultRole.get();
        } else {
            throw new CustomNotFoundException("Did not find role id - " + roleId);
        }

        //asignar el role al usuario
        theUser.setRole(theRole);

        //guardar el usuario
        userRepository.save(theUser);

        Optional<User> result2 = userRepository.findById(userId);

        User theUser2 = null;

        if (result2.isPresent()) {
            theUser2 = result2.get();
        } else {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        return theUser2;
    }

    // add mapping for DELETE /users/{userId} - disable existing user
    @PutMapping("/users/{userId}")
    public User updateEnableUser(@RequestHeader String Authorization, @PathVariable int userId) {

        Optional<User> tempUser = userRepository.findById(userId);

        User theUser = null;

        if (tempUser.isPresent()) {
            theUser = tempUser.get();
        } else {
            throw new CustomNotFoundException("User id not found - " + userId);
        }

        theUser.setStatus(Boolean.TRUE);

        userRepository.save(theUser);

        return theUser;
    }

    // add mapping for DELETE /users/{userId} - disable existing user
    @DeleteMapping("/users/{userId}")

    public User disableUser(@RequestHeader String Authorization, @PathVariable int userId) {

        Optional<User> tempUser = userRepository.findById(userId);

        User theUser = null;

        if (tempUser.isPresent()) {
            theUser = tempUser.get();
            if (theUser.getId() == 1) {
                return new User();
            }
        } else {
            throw new CustomNotFoundException("User id not found - " + userId);
        }

        theUser.setStatus(false);

        userRepository.save(theUser);

        return theUser;
    }

    @PostMapping("/upload/{validator}")
    public List<Employee> upload(@RequestHeader String Authorization, @RequestParam("file") MultipartFile file, @PathVariable String validator) throws IOException {

        List<Map<String, String>> json = uploadService.upload(file);

        List<Employee> empleados = new ArrayList<>();

        List<EmpleadoLayout> empleadosVacios = new ArrayList<>();

        List<String> nombreUsuarios = new ArrayList<>();

        Boolean errorGeneral = Boolean.FALSE;
        Map<String, Integer> compara = new TreeMap<>();
        for (Map<String, String> object : json) {
            for (String employee : object.keySet()) {
                if ("vacio".equals(object.get(employee)) || " ".equals(object.get(employee)) || "  ".equals(object.get(employee)) || "  ".equals(object.get(employee)) || "   ".equals(object.get(employee)) || "    ".equals(object.get(employee)) || "     ".equals(object.get(employee))) { //agregar or epsacios
                    errorGeneral = Boolean.TRUE;
                }

                if (employee.equals("Usuario")) {
                    nombreUsuarios.add(object.get(employee));
                    if (compara.get(object.get(employee)) == null) {
                        compara.put(object.get(employee), 1);
                    } else {
                        Integer x = compara.get(object.get(employee));
                        compara.put(object.get(employee), x + 1);
                    }
                }

            }
        }

        for (String comp : compara.keySet()) {
            if (compara.get(comp) > 1) {
                errorGeneral = Boolean.TRUE;
            }
        }
        Integer count = 2;

        for (Map<String, String> object : json) {

            Boolean errorEmpleado = Boolean.FALSE;
            for (String employee : object.keySet()) {
                if ("vacio".equals(object.get(employee)) || " ".equals(object.get(employee)) || "  ".equals(object.get(employee)) || "  ".equals(object.get(employee)) || "   ".equals(object.get(employee)) || "    ".equals(object.get(employee)) || "     ".equals(object.get(employee))) {
                    errorEmpleado = Boolean.TRUE;
                }

            }
            // System.out.println(compara.toString());

            EmpleadoLayout empleadoLayout = new EmpleadoLayout(object.get("Nombre"), object.get("Apellido Paterno"), object.get("Apellido Materno"), object.get("Edad"), object.get("Sexo"), object.get("Estado Civil"), object.get("Grado Académico"), object.get("Puesto"), object.get("Área"), object.get("Jornada"), Integer.parseInt(object.get("Años laborando")), object.get("Usuario"), object.get("Contraseña"), count);
            count++;
            //traemos el branch
            Optional<Branch> tempBranch = branchRepo.findByValidator(validator);
            Branch theBranch = null;
            if (tempBranch.isPresent()) {
                theBranch = tempBranch.get();
            } else {
                throw new CustomNotFoundException("El centro de trabajo no se encuentra disponible");
            }
            //traermos el area correspondiente
            Area theArea = areaRepo.findByAreaname(empleadoLayout.getÁrea());

            //traermos el puesto correspondiente
            Job theJob = jobRepo.findByJobname(empleadoLayout.getPuesto());

            //traemos la jornada
            Workday theWorkday = workRepo.findByWorkdayname(empleadoLayout.getJornada());

            Employee theEmployee = new Employee();

            theEmployee.setId(0);
            theEmployee.setStatus(Boolean.TRUE);
            theEmployee.setArea(theArea);
            theEmployee.setJob(theJob);
            theEmployee.setWorkday(theWorkday);
            theEmployee.setBranch(theBranch);

            theEmployee.setAge((Integer.parseInt(empleadoLayout.getEdad())));
            theEmployee.setEducation(empleadoLayout.getGrado_Académico());
            theEmployee.setEmployeeName(empleadoLayout.getApellido_Paterno() + " " + empleadoLayout.getApellido_Materno() + " " + empleadoLayout.getNombre());
            theEmployee.setMaritalStatus(empleadoLayout.getEstado_Civil());
            theEmployee.setSex(empleadoLayout.getSexo());
            theEmployee.setWorkYears(empleadoLayout.getAños_laborando());
            //

            User theUser = new User();
            theUser.setStatus(Boolean.TRUE);

            //validar username unico
            theUser.setUsername(theBranch.getId() + "-" + empleadoLayout.getUsuario());
            User validUsername = userRepo.findByUsername(theUser.getUsername());

            if (validUsername != null && errorGeneral == Boolean.FALSE) {
                throw new CustomBadRequestException("El usuario ya se encuentra registrado");
            }

            //validar password
            Pattern pat = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()-=,.:\\{}<>\\\"\\u00f1\\u00d1']*$");
            Matcher mat = pat.matcher(empleadoLayout.getContraseña());

            if (!mat.matches()) {
                throw new RuntimeException("Solo caracteres validos");
            }

            if (empleadoLayout.getContraseña().length() < 4 || empleadoLayout.getContraseña().length() > 16) {
                throw new RuntimeException("La contrasena debe estar entre 4 y 16 caracteres");
            }

            //encriptar pass
            theUser.setPassword(bcryptEncoder.encode(empleadoLayout.getContraseña()));
            //buscar role
            Optional<Role> tempRole = roleRepo.findByName("EMPLOYEE");
            Role theRole = null;

            if (tempRole.isPresent()) {
                theRole = tempRole.get();
            } else {
                throw new RuntimeException("No es encontro el rol");
            }

            theUser.setRole(theRole);

            theEmployee.setUser(theUser);

            if (!errorGeneral) {
                employeeRepo.save(theEmployee);
                Employee tempEmployee = employeeRepo.findByEmployeeNameAndBranchAndWorkYears(theEmployee.getEmployeeName(), theEmployee.getBranch(), theEmployee.getWorkYears());

                //traemos las encuestas del branch
                List<Survey> surveys = theBranch.getSurveys();

                for (Survey survey : surveys) {
                    if (survey.getId() != 3) {
                        EmployeeSurvey ems = new EmployeeSurvey(0, tempEmployee, survey, Boolean.TRUE);
                        emsRepo.save(ems);
                    }

                }
            }

            if (errorEmpleado) {
                empleadoLayout.setTipoError("Campos Vacios");
                empleadosVacios.add(empleadoLayout);
            } else if (compara.get(empleadoLayout.getUsuario()) > 1) {
                empleadoLayout.setTipoError("Usuario Repetido");
                empleadosVacios.add(empleadoLayout);
            } else {
                empleados.add(theEmployee);
            }

        }
        if (errorGeneral) {
            throw new CustomBadRequestException(empleadosVacios.toString());
        } else {
            return empleados;
        }

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

    // add exception handler to catch all
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(Exception exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
