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
public class BranchAreasJobsUpdate {

    private Integer Idbranch;

    private List<Integer> areas;

    private List<Integer> jobs;

    public Integer getIdbranch() {
        return Idbranch;
    }

    public void setIdbranch(Integer Idbranch) {
        this.Idbranch = Idbranch;
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

    public BranchAreasJobsUpdate() {
    }

    public BranchAreasJobsUpdate(Integer Idbranch, List<Integer> areas, List<Integer> jobs) {
        this.Idbranch = Idbranch;
        this.areas = areas;
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return "BranchAreasJobsUpdate{" + "Idbranch=" + Idbranch + ", areas=" + areas + ", jobs=" + jobs + '}';
    }

}
