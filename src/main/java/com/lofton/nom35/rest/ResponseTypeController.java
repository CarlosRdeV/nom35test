/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Category;
import com.lofton.nom35.Entity.ResponseType;
import com.lofton.nom35.Repository.CategoryRepository;
import com.lofton.nom35.Repository.ResponseTypeRepository;
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
public class ResponseTypeController {

    @Autowired
    private ResponseTypeRepository responseTypeRepo;

    @GetMapping("/types")
    public List<ResponseType> getFindAll(@RequestHeader String Authorization) {
        return responseTypeRepo.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/types/{typeId}")
    public ResponseType getType(@RequestHeader String Authorization, @PathVariable int typeId) {

        Optional<ResponseType> result = responseTypeRepo.findById(typeId);

        ResponseType theType = null;

        if (result.isPresent()) {
            theType = result.get();
        } else {
            throw new CustomNotFoundException("Did not find Response Type id - " + typeId);

        }

        return theType;

    }

    // // add mapping for POST /users - add new user 
    @PostMapping("/types")
    public ResponseType addType(@RequestHeader String Authorization, @RequestBody ResponseType theType) {

        theType.setId(0);

        responseTypeRepo.save(theType);

        ResponseType tempType = responseTypeRepo.findByName(theType.getName());

        return tempType;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/types")
    public ResponseType updateType(@RequestHeader String Authorization, @RequestBody ResponseType theType) {
        responseTypeRepo.save(theType);
        return theType;
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
