package com.lofton.nom35.rest;

import com.lofton.nom35.Entity.Area;
import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Entity.Job;
import com.lofton.nom35.Repository.AreaRepository;
import com.lofton.nom35.Repository.BranchRepository;
import com.lofton.nom35.Repository.JobRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class JobController {

    private JobRepository jobRepository;

    private BranchRepository branchRepository;

    @Autowired
    public JobController(JobRepository jobRepository, BranchRepository branchRepository) {
        this.jobRepository = jobRepository;
        this.branchRepository = branchRepository;
    }

    @GetMapping("/jobs")
    public List<Job> getFindAll(@RequestHeader String Authorization) {
        return jobRepository.findAll();
    }

    // add mapping for GET /areas/{areaId} - return area if exists
    @GetMapping("/jobs/{jobId}")
    public Job getJob(@RequestHeader String Authorization, @PathVariable int jobId) {

        Optional<Job> result = jobRepository.findById(jobId);

        Job theJob = null;

        if (result.isPresent()) {
            theJob = result.get();
        } else {
            throw new CustomNotFoundException("Did not find job id - " + jobId);
        }

        return theJob;

    }

    // // add mapping for POST /users - add new user 
    @PostMapping("/jobs")
    public Job addJob(@RequestHeader String Authorization, @RequestBody Job theJob) {

        theJob.setId(0);
        theJob.setStatus(Boolean.TRUE);

        jobRepository.save(theJob);

        Job tempJob = jobRepository.findByJobname(theJob.getJobname());

        return tempJob;

    }

    // add mapping for PUT /areas - add new user 
    @PutMapping("/jobs")
    public Job updateJob(@RequestHeader String Authorization, @RequestBody Job theJob) {
        jobRepository.save(theJob);
        return theJob;
    }

    // add mapping for DELETE /users/{userId} - disable existing user
    @DeleteMapping("/jobs/{jobId}")
    public Job disableJob(@RequestHeader String Authorization, @PathVariable int jobId) {

        Optional<Job> tempJob = jobRepository.findById(jobId);

        Job theJob = null;

        if (tempJob.isPresent()) {
            theJob = tempJob.get();
        } else {
            throw new RuntimeException("Job id not found - " + jobId);
        }

        theJob.setStatus(false);

        jobRepository.save(theJob);

        return theJob;
    }

    // add mapping for PUT /games/{gameId}/{genreId} - add existing genre to a existing game
    @PutMapping("/jobs/branches/{jobId}/{branchId}")
    public List<Job> addJobBranch(@RequestHeader String Authorization, @PathVariable int jobId, @PathVariable int branchId) {

        Optional<Branch> tempBranch = branchRepository.findById(branchId);

        Branch theBranch = null;

        Optional<Job> tempJob = jobRepository.findById(jobId);

        Job theJob = null;

        if (tempBranch.isPresent()) {

            if (tempJob.isPresent()) {
                theBranch = tempBranch.get();
                theJob = tempJob.get();

            } else {
                throw new RuntimeException("Job id not found - " + jobId);
            }

        } else {
            throw new RuntimeException("Branch id not found - " + branchId);
        }

        theBranch.addJob(theJob);

        branchRepository.save(theBranch);

        return theBranch.getJobs();
    }
}
