package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Area;
import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Entity.Employee;
import com.lofton.nom35.Entity.EmployeeSurvey;
import com.lofton.nom35.Entity.Job;
import com.lofton.nom35.Entity.JwtRequest;
import com.lofton.nom35.Entity.JwtResponse;
import com.lofton.nom35.Entity.Role;
import com.lofton.nom35.Entity.Survey;
import com.lofton.nom35.Entity.Token;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Entity.Workday;
import com.lofton.nom35.Repository.AreaRepository;
import com.lofton.nom35.Repository.BranchRepository;
import com.lofton.nom35.Repository.EmployeeRepository;
import com.lofton.nom35.Repository.EmployeeSurveyRepository;
import com.lofton.nom35.Repository.JobRepository;
import com.lofton.nom35.Repository.RoleRepository;
import com.lofton.nom35.Repository.TokenRepository;
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.Repository.WorkdayRepository;
import com.lofton.nom35.process.branch.BranchValidator;
import com.lofton.nom35.service.JwtUserDetailsService;
import com.lofton.nom35.springsecurity.config.JwtTokenUtil;
import com.lofton.nom35.templates.EmpleadoUsuario;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RestController
@CrossOrigin
@RequestMapping
public class JwtAuthenticationController {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private TokenRepository tokenRepo;
    private UserRepository userRepo;
    private EmployeeRepository employeeRepository;
    private BranchRepository branchRepository;
    private AreaRepository areaRepository;
    private JobRepository jobRepository;
    private WorkdayRepository workdayRepository;
    private PasswordEncoder bcryptEncoder;
    private RoleRepository roleRepo;
    private EmployeeSurveyRepository emsRepo;

    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, TokenRepository tokenRepo, UserRepository userRepo, EmployeeRepository employeeRepository, BranchRepository branchRepository, AreaRepository areaRepository, JobRepository jobRepository, WorkdayRepository workdayRepository, PasswordEncoder bcryptEncoder, RoleRepository roleRepo, EmployeeSurveyRepository emsRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.tokenRepo = tokenRepo;
        this.userRepo = userRepo;
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.areaRepository = areaRepository;
        this.jobRepository = jobRepository;
        this.workdayRepository = workdayRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepo = roleRepo;
        this.emsRepo = emsRepo;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        User theUser = userRepo.findByUsername(authenticationRequest.getUsername());

        if (!theUser.getStatus()) {
            throw new CustomUnauthorizedException("Usuario deshabilitado");
        }

        //buscar el token asignado
        Optional<Token> tempToken = tokenRepo.findByUser(theUser);

        Token theToken1 = null;
        if (tempToken.isPresent()) {
            theToken1 = tempToken.get();
            theToken1.setToken(token);

            tokenRepo.save(theToken1);
        } else {
            Token theToken = new Token();
            theToken.setId(0);
            theToken.setToken(token);
            theToken.setUser(theUser);

            tokenRepo.save(theToken);

        }

        return ResponseEntity.ok(new JwtResponse(token, theUser.getRole(), theUser.getUsername()));
    }

    @PostMapping("/authenticate/{validator}")
    public ResponseEntity<?> createAuthenticationTokenEmployee(@PathVariable String validator, @RequestBody JwtRequest authenticationRequest) throws Exception {

        Optional<Branch> tempBranch = branchRepository.findByValidator(validator);

        Branch theBranch = null;

        if (tempBranch.isPresent()) {
            theBranch = tempBranch.get();
        }

        String usernameEmployee = theBranch.getId() + "-" + authenticationRequest.getUsername();

        authenticationRequest.setUsername(usernameEmployee);

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        User theUser = userRepo.findByUsername(authenticationRequest.getUsername());

        if (!theUser.getStatus()) {
            throw new CustomUnauthorizedException("Usuario deshabilitado");
        }

        //buscar el token asignado
        Optional<Token> tempToken = tokenRepo.findByUser(theUser);

        Token theToken1 = null;
        if (tempToken.isPresent()) {
            theToken1 = tempToken.get();
            theToken1.setToken(token);

            tokenRepo.save(theToken1);
        } else {
            Token theToken = new Token();
            theToken.setId(0);
            theToken.setToken(token);
            theToken.setUser(theUser);

            tokenRepo.save(theToken);

        }

        return ResponseEntity.ok(new JwtResponse(token, theUser.getRole(), theUser.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User theUser) throws Exception {

//        User tempUser = userRepo.findByUsername(theUser.getUsername());
//        System.out.println(tempUser);
        Pattern pat = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()-=,.:\\{}<>\\\"\\u00f1\\u00d1']*$");
        Matcher mat = pat.matcher(theUser.getPassword());

        if (!mat.matches()) {
            throw new RuntimeException("Solo caracteres validos");
        }

        if (theUser.getPassword().length() < 4 || theUser.getPassword().length() > 16) {
            throw new RuntimeException("La contrasena debe estar entre 4 y 16 caracteres");
        }

        theUser.setId(0);
        theUser.setStatus(Boolean.TRUE);
        return ResponseEntity.ok(userDetailsService.save(theUser));
    }
    ///////////////
    //AQUI/////////
    ///////////////

    @PostMapping("/register/employees/{validator}/{areaId}/{jobId}/{workdayId}")
    public User addEmployeeUser(
            @RequestBody EmpleadoUsuario employeeUser,
            @PathVariable String validator,
            @PathVariable int areaId,
            @PathVariable int jobId,
            @PathVariable int workdayId) {

        Employee theEmployee = new Employee();

        theEmployee.setId(0);
        theEmployee.setStatus(Boolean.TRUE);
        theEmployee.setEmployeeName(employeeUser.getEmployeeName());
        theEmployee.setSex(employeeUser.getSex());
        theEmployee.setAge(employeeUser.getAge());
        theEmployee.setMaritalStatus(employeeUser.getMaritalStatus());
        theEmployee.setEducation(employeeUser.getEducation());
        theEmployee.setWorkYears(employeeUser.getWorkYears());

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

        User theUser = new User();
        theUser.setStatus(Boolean.TRUE);

        //validar username unico
        theUser.setUsername(tempBranch.getId() + "-" + employeeUser.getUsername());
        User validUsername = userRepo.findByUsername(theUser.getUsername());

        if (validUsername != null) {
            throw new RuntimeException("El usuario ya se encuentra registrado");
        }

        //validar password
        Pattern pat = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()-=,.:\\{}<>\\\"\\u00f1\\u00d1']*$");
        Matcher mat = pat.matcher(employeeUser.getPassword());

        if (!mat.matches()) {
            throw new RuntimeException("Solo caracteres validos");
        }

        if (employeeUser.getPassword().length() < 4 || employeeUser.getPassword().length() > 16) {
            throw new RuntimeException("La contrasena debe estar entre 4 y 16 caracteres");
        }

        //encriptar pass
        theUser.setPassword(bcryptEncoder.encode(employeeUser.getPassword()));
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
        employeeRepository.save(theEmployee);

        Employee tempEmployee = employeeRepository.findByEmployeeNameAndBranchAndWorkYears(theEmployee.getEmployeeName(), tempBranch, theEmployee.getWorkYears());

        //traemos las encuestas del branch
        List<Survey> surveys = tempBranch.getSurveys();

        for (Survey survey : surveys) {
            EmployeeSurvey ems = new EmployeeSurvey(0, tempEmployee, survey, Boolean.TRUE);
            emsRepo.save(ems);
        }
        return theUser;

    }

    @PostMapping("/deleteToken")
    public void deleteToken(@RequestHeader String Authorization) throws Exception {

        String[] array = Authorization.split(" ");

        System.out.println(array[1]);

        Optional<Token> token = tokenRepo.findByToken(array[1]);

        Token tempToken = null;
        if (token.isPresent()) {
            tempToken = token.get();

            tokenRepo.deleteById(tempToken.getId());

        }

    }

    @GetMapping("/branches/valid/{validator}")
    public Branch getBranchValidator(@PathVariable String validator) {
        Optional<Branch> result = branchRepository.findByValidator(validator);
        Branch theBranch = null;
        if (result.isPresent()) {
            theBranch = result.get();
        } else {
            throw new RuntimeException("Did not find validator - " + validator);
        }
        return theBranch;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new CustomBadRequestException("INVALID_CREDENTIALS", e);
        }
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomBadRequestException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomUnauthorizedException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
