/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CGCSTF08
 */
@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "status")
    private Boolean status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "survey")
    private List<Question> questions;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "branch_has_survey",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    private List<Branch> branches;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "rangos")
    private String rangos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id")
    private List<Section> sections;

    @OneToMany(mappedBy = "survey")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<EmployeeSurvey> employeeSurveys;

    public String getRangos() {
        return rangos;
    }

    public void setRangos(String rangos) {
        this.rangos = rangos;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Survey() {
    }

    public Survey(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Survey{" + "id=" + id + ", name=" + name + ", status=" + status + '}';
    }

    public void addQuestions(Question theQuestion) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(theQuestion);
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public void addSection(Section theSection) {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        sections.add(theSection);
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<EmployeeSurvey> getEmployeeSurveys() {
        return employeeSurveys;
    }

    public void setEmployeeSurveys(List<EmployeeSurvey> employeeSurveys) {
        this.employeeSurveys = employeeSurveys;
    }
}
