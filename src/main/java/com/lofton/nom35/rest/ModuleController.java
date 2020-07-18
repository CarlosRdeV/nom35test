/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Job;
import com.lofton.nom35.Entity.Module;
import com.lofton.nom35.Entity.ModulePermiso;
import com.lofton.nom35.Entity.Role;
import com.lofton.nom35.Entity.User;
import com.lofton.nom35.Repository.ModuleRepository;
import com.lofton.nom35.Repository.UserRepository;
import com.lofton.nom35.process.permission.CheckPermissions;
import static com.lofton.nom35.process.permission.CheckPermissions.checkPermissions;
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

/**
 *
 * @author CGCSTF08
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ModuleController {

    // quick use of repository directly
    private ModuleRepository moduleRepo;

    private UserRepository userRepo;

    // constructor injection
    @Autowired
    public ModuleController(ModuleRepository theModuleRepo, UserRepository theUserRepository) {
        moduleRepo = theModuleRepo;
        userRepo = theUserRepository;
    }

    // add mapping for GET /users - return list of all users
    @GetMapping("/modules")
    public List<Module> getFindAll(@RequestHeader String Authorization) {

        return moduleRepo.findAll();
    }

    @GetMapping("/modules/{moduleId}")
    public Module getModule(@RequestHeader String Authorization, @PathVariable int moduleId) {

        Optional<Module> tempModule = moduleRepo.findById(moduleId);
        Module theModule = null;
        if (tempModule.isPresent()) {
            theModule = tempModule.get();
        } else {
            throw new CustomNotFoundException("Module id not found " + moduleId);
        }
        return theModule;
    }

    @PostMapping("/modules")
    public Module addModule(@RequestHeader String Authorization, @RequestBody Module theModule) {

        theModule.setId(0);
        theModule.setStatus(Boolean.TRUE);

        moduleRepo.save(theModule);

        Module tempModule = moduleRepo.findByName(theModule.getName());

        return tempModule;
    }

    @PutMapping("/modules")
    public Module updateModule(@RequestHeader String Authorization, @RequestBody Module theModule) {

        moduleRepo.save(theModule);

        return theModule;
    }

    @DeleteMapping("/modules/{moduleId}")
    public Module disableModule(@RequestHeader String Authorization, @PathVariable int moduleId) {

        Optional<Module> tempModule = moduleRepo.findById(moduleId);
        Module theModule = null;
        if (tempModule.isPresent()) {
            theModule = tempModule.get();
            theModule.setStatus(Boolean.FALSE);

            moduleRepo.save(theModule);
        } else {
            throw new CustomNotFoundException("Module id not found " + moduleId);
        }

        return theModule;
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
    public ResponseEntity<CustomErrorResponse> handlerException(CustomUnauthorizedException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
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
