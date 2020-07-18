/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Dimension;
import com.lofton.nom35.Entity.Question;
import com.lofton.nom35.Entity.ResponseType;
import com.lofton.nom35.Entity.Survey;
import com.lofton.nom35.Repository.DimensionRepository;
import com.lofton.nom35.Repository.QuestionRepository;
import com.lofton.nom35.Repository.ResponseTypeRepository;
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
public class QuestionController {

    private QuestionRepository questionRepo;

    private DimensionRepository dimensionRepo;

    private ResponseTypeRepository typeRepo;

    private SurveyRepository surveyRepo;

    @Autowired
    public QuestionController(QuestionRepository questionRepo, DimensionRepository dimensionRepo, ResponseTypeRepository typeRepo, SurveyRepository surveyRepo) {
        this.questionRepo = questionRepo;
        this.dimensionRepo = dimensionRepo;
        this.typeRepo = typeRepo;
        this.surveyRepo = surveyRepo;
    }

    @GetMapping("/questions")
    public List<Question> getFindAll(@RequestHeader String Authorization) {
        return questionRepo.findAll();
    }

    @GetMapping("/questions/surveys/{surveyId}")
    public List<Question> getFindAllSurvey(@RequestHeader String Authorization, @PathVariable int surveyId) {
        Optional<Survey> result = surveyRepo.findById(surveyId);

        Survey theSurvey = null;

        if (result.isPresent()) {
            theSurvey = result.get();
        } else {
            throw new CustomNotFoundException("Did not find question id - " + surveyId);

        }

        return questionRepo.findBySurvey(theSurvey);
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/questions/{questionId}")
    public Question getQuestion(@RequestHeader String Authorization, @PathVariable int questionId) {

        Optional<Question> result = questionRepo.findById(questionId);

        Question theQuestion = null;

        if (result.isPresent()) {
            theQuestion = result.get();
        } else {
            throw new CustomNotFoundException("Did not find question id - " + questionId);

        }

        return theQuestion;

    }

    @PostMapping("/questions/{dimensionId}/{responseTypeId}/{surveyId}")
    public Question addQuestion(
            @RequestHeader String Authorization,
            @RequestBody Question theQuestion,
            @PathVariable int dimensionId,
            @PathVariable int responseTypeId,
            @PathVariable int surveyId) {

        Optional<Dimension> result = dimensionRepo.findById(dimensionId);

        theQuestion.setId(0);

        Dimension theDimension = null;

        if (result.isPresent()) {
            theDimension = result.get();
        } else {
            throw new CustomNotFoundException("Did not find dimension id - " + dimensionId);
        }

        theQuestion.setDimension(theDimension);

        // guardar en responseType
        Optional<ResponseType> resultType = typeRepo.findById(responseTypeId);

        ResponseType theType = null;

        if (resultType.isPresent()) {
            theType = resultType.get();
        } else {
            throw new CustomNotFoundException("Did not find resultType id - " + responseTypeId);
        }

        theQuestion.setResponseType(theType);

        // guardar en survey
        Optional<Survey> resultSurvey = surveyRepo.findById(surveyId);

        Survey theSurvey = null;

        if (resultSurvey.isPresent()) {
            theSurvey = resultSurvey.get();
        } else {
            throw new CustomNotFoundException("Did not find Survey id - " + surveyId);
        }

        theQuestion.setSurvey(theSurvey);

        questionRepo.save(theQuestion);

        return theQuestion;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/questions")
    public Question updateQuestion(@RequestHeader String Authorization, @RequestBody Question theQuestion) {
        questionRepo.save(theQuestion);
        return theQuestion;
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
