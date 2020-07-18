package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Category;
import com.lofton.nom35.Repository.CategoryRepository;
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
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/categories")
    public List<Category> getFindAll(@RequestHeader String Authorization) {
        return categoryRepo.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/categories/{categoryId}")
    public Category getCategories(@RequestHeader String Authorization, @PathVariable int categoryId) {

        Optional<Category> result = categoryRepo.findById(categoryId);

        Category theCategory = null;

        if (result.isPresent()) {
            theCategory = result.get();
        } else {
            throw new CustomNotFoundException("Did not find category id - " + categoryId);

        }

        return theCategory;

    }

    // // add mapping for POST /users - add new user 
    @PostMapping("/categories")
    public Category addCategory(@RequestHeader String Authorization, @RequestBody Category theCategory) {

        theCategory.setId(0);

        categoryRepo.save(theCategory);

        Category tempCategory = categoryRepo.findByName(theCategory.getName());

        return tempCategory;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/categories")
    public Category updateCategory(@RequestHeader String Authorization, @RequestBody Category theCategory) {
        categoryRepo.save(theCategory);
        return theCategory;
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

    //add exception handler for not found 
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handlerException(CustomNotFoundException exc) {
        CustomErrorResponse error = new CustomErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
