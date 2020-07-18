package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Area;
import com.lofton.nom35.Entity.Employee;
import com.lofton.nom35.Entity.EmployeeSurvey;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Repository.EmployeeRepository;
import com.lofton.nom35.Repository.EmployeeSurveyRepository;
import com.lofton.nom35.Repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class EmployeeSurveyController {

    @Autowired
    private EmployeeSurveyRepository esRepo;
    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/emp")
    public List<EmployeeSurvey> getFindAll(@RequestHeader String Authorization) {
        return esRepo.findAll();
    }

    @GetMapping("/emp/{employeeUser}")
    public List<EmployeeSurvey> getFindAll(
            @RequestHeader String Authorization,
            @PathVariable String employeeUser) {

        User theUser = userRepo.findByUsername(employeeUser);

        Optional<Employee> tempEmployee = employeeRepo.findByUser(theUser);
        Employee theEmployee = null;

        if (tempEmployee.isPresent()) {
            theEmployee = tempEmployee.get();
        }
        return esRepo.findByEmployee(theEmployee);
    }

}
