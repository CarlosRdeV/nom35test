/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Category;
import com.lofton.nom35.Entity.Dimension;
import com.lofton.nom35.Entity.Domain;
import com.lofton.nom35.Repository.DimensionRepository;
import com.lofton.nom35.Repository.DomainRepository;
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
public class DimensionController {

    @Autowired
    private DimensionRepository dimensionRepo;

    @Autowired
    private DomainRepository domainRepo;

    @GetMapping("/dimensions")
    public List<Dimension> getFindAll(@RequestHeader String Authorization) {
        return dimensionRepo.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/dimensions/{dimensionId}")
    public Dimension getDimension(@RequestHeader String Authorization, @PathVariable int dimensionId) {

        Optional<Dimension> result = dimensionRepo.findById(dimensionId);

        Dimension theDimension = null;

        if (result.isPresent()) {
            theDimension = result.get();
        } else {
            throw new CustomNotFoundException("Did not find dimension id - " + dimensionId);

        }

        return theDimension;

    }

    // // add mapping for POST /users - add new user 
    @PostMapping("/dimensions/{domainId}")
    public Dimension addDimension(@RequestHeader String Authorization, @RequestBody Dimension theDimension, @PathVariable int domainId) {

        Optional<Domain> result = domainRepo.findById(domainId);

        Domain theDomain = null;

        if (result.isPresent()) {
            theDomain = result.get();
        } else {
            throw new CustomNotFoundException("Did not find Domain id - " + domainId);
        }

        theDimension.setId(0);

        theDomain.addDimension(theDimension);

        domainRepo.save(theDomain);

        Dimension tempDimension = dimensionRepo.findByName(theDimension.getName());

        return tempDimension;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/dimensions")
    public Dimension updateDimension(@RequestHeader String Authorization, @RequestBody Dimension theDimension) {
        dimensionRepo.save(theDimension);
        return theDimension;
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
