/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Category;
import com.lofton.nom35.Entity.Survey;
import com.lofton.nom35.Repository.CategoryRepository;
import com.lofton.nom35.Repository.SurveyRepository;
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
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepo;

    @GetMapping("/surveys")
    public List<Survey> getFindAll(@RequestHeader String Authorization) {
        return surveyRepo.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/surveys/{surveyId}")
    public Survey getSurvey(@RequestHeader String Authorization, @PathVariable int surveyId) {

        Optional<Survey> result = surveyRepo.findById(surveyId);

        Survey theSurvey = null;

        if (result.isPresent()) {
            theSurvey = result.get();
        } else {
            throw new CustomNotFoundException("Did not find survey id - " + surveyId);

        }

        return theSurvey;

    }

    // // add mapping for POST /users - add new user 
    @PostMapping("/surveys")
    public Survey addSurvey(@RequestHeader String Authorization, @RequestBody Survey theSurvey) {

        theSurvey.setId(0);
        theSurvey.setStatus(Boolean.TRUE);

        surveyRepo.save(theSurvey);

        Survey tempSurvey = surveyRepo.findByName(theSurvey.getName());

        return tempSurvey;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/surveys")
    public Survey updateSurvey(@RequestHeader String Authorization, @RequestBody Survey theSurvey) {
        surveyRepo.save(theSurvey);
        return theSurvey;
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
