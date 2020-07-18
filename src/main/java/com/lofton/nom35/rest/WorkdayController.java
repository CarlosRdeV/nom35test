/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Workday;
import com.lofton.nom35.Repository.WorkdayRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class WorkdayController {

    private WorkdayRepository workdayRepo;

    @Autowired
    public WorkdayController(WorkdayRepository workdayRepo) {
        this.workdayRepo = workdayRepo;
    }

    @GetMapping("/workdays")
    public List<Workday> getFindAll(@RequestHeader String Authorization) {
        return workdayRepo.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/workdays/{workdayId}")
    public Workday getWorkday(@RequestHeader String Authorization, @PathVariable int workdayId) {

        Optional<Workday> result = workdayRepo.findById(workdayId);

        Workday theWorkday = null;

        if (result.isPresent()) {
            theWorkday = result.get();
        } else {
            throw new CustomNotFoundException("Did not find workday id - " + workdayId);
        }

        return theWorkday;

    }

    // // add mapping for POST /users - add new user 
    @PostMapping("/workdays")
    public Workday addWorkday(@RequestHeader String Authorization, @RequestBody Workday theWorkday) {

        theWorkday.setId(0);

        workdayRepo.save(theWorkday);

        Workday tempWorkday = workdayRepo.findByWorkdayname(theWorkday.getWorkdayname());

        return tempWorkday;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/workdays")
    public Workday updateWorkday(@RequestHeader String Authorization, @RequestBody Workday theWorkday) {
        workdayRepo.save(theWorkday);
        return theWorkday;
    }

}
