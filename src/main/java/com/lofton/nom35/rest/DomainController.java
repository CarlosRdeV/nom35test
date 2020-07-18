package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Category;
import com.lofton.nom35.Entity.Domain;
import com.lofton.nom35.Repository.CategoryRepository;
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
public class DomainController {

    @Autowired
    private DomainRepository domainRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/domains")
    public List<Domain> getFindAll(@RequestHeader String Authorization) {
        return domainRepo.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/domains/{domainId}")
    public Domain getDomain(@RequestHeader String Authorization, @PathVariable int domainId) {

        Optional<Domain> result = domainRepo.findById(domainId);

        Domain theDomain = null;

        if (result.isPresent()) {
            theDomain = result.get();
        } else {
            throw new CustomNotFoundException("Did not find domains id - " + domainId);

        }

        return theDomain;

    }

    // // add mapping for POST /users - add new user 
    @CrossOrigin(origins = "*", allowedHeaders = "*") 
    @PostMapping("/domains/{categoryId}")
    public Domain addDomain(@RequestHeader String Authorization, @RequestBody Domain theDomain, @PathVariable int categoryId) {

        Optional<Category> result = categoryRepo.findById(categoryId);

        Category theCategory = null;

        if (result.isPresent()) {
            theCategory = result.get();
        } else {
            throw new CustomNotFoundException("Did not find category id - " + categoryId);
        }

        theDomain.setId(0);

        theCategory.addDomain(theDomain);

        categoryRepo.save(theCategory);

        Domain tempDomain = domainRepo.findByName(theDomain.getName());

        return tempDomain;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/domains")
    public Domain updateDomain(@RequestHeader String Authorization, @RequestBody Domain theDomain) {
        domainRepo.save(theDomain);
        return theDomain;
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
