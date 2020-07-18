/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "branch")
public class Branch {

    //////////////
    //  FIELDS  //
    //////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_branch")
    private Integer id;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "name")
    @Size(min = 4, max = 128, message = "El nombre del centro de trabajo debe estar entre 4 y 128 caracteres")
    private String name;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "address")
    @Size(min = 4, max = 160, message = "La direccion del centro de trabajo debe estar entre 4 y 160 caracteres")
    private String address;

    @NotNull
    @Positive
    @Column(name = "employees")
    private Integer employees;

    @NotNull
    @Column(name = "min_respondents")
    private Integer min_respondents;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "branch_type")
    @Size(min = 4, max = 64, message = "La rama del centro de trabajo debe estar entre 4 y 64 caracteres")
    private String branchType;

    @NotNull
    @Column(name = "validator", unique = true)
    @Size(max = 5)
    private String validator;

    @NotNull
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    ///////////////////////
    //  RELATION FIELDS  //
    ///////////////////////
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @ManyToMany
    @JoinTable(
            name = "area_has_branch",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id")
    )
    private List<Area> areas;

    @ManyToMany
    @JoinTable(
            name = "branch_has_survey",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id")
    )
    private List<Survey> surveys;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "job_has_branch",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Job> jobs;

    ///////////
    //  G&S  //
    ///////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public Integer getMin_respondents() {
        return min_respondents;
    }

    public void setMin_respondents(Integer min_respondents) {
        this.min_respondents = min_respondents;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;

    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Branch() {
    }

    public Branch(String name, String address, Integer employees, Integer min_respondents, String branchType, Boolean status) {
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.min_respondents = min_respondents;
        this.branchType = branchType;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Branch{" + "id=" + id + ", name=" + name + ", address=" + address + ", employees=" + employees + ", min_respondents=" + min_respondents + ", branchType=" + branchType + ", validator=" + validator + ", status=" + status + ", enterprise=" + enterprise + '}';
    }

    // convenience methods
    public void addArea(Area theArea) {
        if (areas == null) {
            areas = new ArrayList<>();
        }
        areas.add(theArea);
    }

    public void addSurvey(Survey theSurvey) {
        if (surveys == null) {
            surveys = new ArrayList<>();
        }
        surveys.add(theSurvey);
    }

    public void addJob(Job theJob) {
        if (jobs == null) {
            jobs = new ArrayList<>();
        }
        jobs.add(theJob);
    }

}
