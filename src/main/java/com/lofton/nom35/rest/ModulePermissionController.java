/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Module;
import com.lofton.nom35.Entity.ModulePermiso;
import com.lofton.nom35.Entity.Role;
import com.lofton.nom35.Repository.ModulePermisoRepository;
import com.lofton.nom35.Repository.ModuleRepository;
import com.lofton.nom35.templates.ModuleIdPermisos;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ModulePermissionController {

    @Autowired
    private ModulePermisoRepository mpRepo;

    @Autowired
    private ModuleRepository moduleRepo;

    @GetMapping("/permissions")
    public List<ModulePermiso> getFindAll(@RequestHeader String Authorization) {
        return mpRepo.findAll();
    }

    @GetMapping("/permissions/{mpId}")
    public ModulePermiso getModulePermiso(@RequestHeader String Authorization, @PathVariable int mpId) {
        Optional<ModulePermiso> tempMp = mpRepo.findById(mpId);
        ModulePermiso theModulePermiso = null;
        if (tempMp.isPresent()) {
            theModulePermiso = tempMp.get();
        } else {
            throw new CustomNotFoundException("Module - Permission id not found " + mpId);
        }
        return theModulePermiso;
    }

    @PostMapping("/permissions")
    public ModulePermiso addModulePermiso(@RequestHeader String Authorization, @RequestBody ModuleIdPermisos theModuleIdPermisos) {
        Optional<Module> theModule = moduleRepo.findById(theModuleIdPermisos.getModuleId());

        Module tempModule = null;
        if (theModule.isPresent()) {
            tempModule = theModule.get();
        } else {
            throw new CustomNotFoundException("Module - Permission id not found " + theModuleIdPermisos.getModuleId());
        }

        ModulePermiso mp = new ModulePermiso(tempModule, theModuleIdPermisos.getPermisos());
        mpRepo.save(mp);

        return mp;
    }

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
