/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

import com.lofton.nom35.Entity.Area;
import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Entity.Job;
import java.util.List;

/**
 *
 * @author CGCSTF08
 */
public class BranchAreasJobs {

    private Branch branch;

    private List<Integer> areas;

    private List<Integer> jobs;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Integer> getAreas() {
        return areas;
    }

    public void setAreas(List<Integer> areas) {
        this.areas = areas;
    }

    public List<Integer> getJobs() {
        return jobs;
    }

    public void setJobs(List<Integer> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "BranchAreasJobs{" + "branch=" + branch + ", areas=" + areas + ", jobs=" + jobs + '}';
    }

    public BranchAreasJobs(Branch branch, List<Integer> areas, List<Integer> jobs) {
        this.branch = branch;
        this.areas = areas;
        this.jobs = jobs;
    }

    public BranchAreasJobs() {
    }

   
}
