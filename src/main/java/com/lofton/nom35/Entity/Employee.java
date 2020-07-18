/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 *
 * @author CGCSTF01
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Integer id;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "employeeName")
    @Size(min = 4, max = 128, message = "El nombre del empleado debe estar entre 4 y 128 caracteres")
    private String employeeName;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Pattern(
            regexp = "^(?:m|M|h|H)$",
            message = "Only M or H") // solo hombre-mujer
    @Column(name = "sex", columnDefinition = "varchar(1)")
    private String sex;

    @NotNull
    @Positive
    @Column(name = "age")
    private Integer age;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "marital_status")
    @Size(min = 4, max = 32, message = "El estatus marital debe estar entre 4 y 32 caracteres")
    private String maritalStatus;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "education")
    @Size(min = 4, max = 32, message = "El campo del educacion debe estar entre 4 y 32 caracteres")
    private String education;

    @NotNull
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    @NotNull
    @PositiveOrZero
    @Column(name = "work_years")
    private Integer workYears;

    ///////////////////////
    //  RELATION FIELDS  //
    ///////////////////////
    @OneToMany(mappedBy = "employee")
    private List<Response> responses;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeSurvey> employeeSurveys;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "workday_id")
    private Workday workday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    ///////////
    //  G&S  //
    ///////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getWorkYears() {
        return workYears;
    }

    public void setWorkYears(Integer workYears) {
        this.workYears = workYears;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Workday getWorkday() {
        return workday;
    }

    public void setWorkday(Workday workday) {
        this.workday = workday;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee() {
    }

    public Employee(String employeName, String sex, Integer age, String maritalStatus, String eduation, Boolean status, Integer workYears) {
        this.employeeName = employeName;
        this.sex = sex;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.education = eduation;
        this.status = status;
        this.workYears = workYears;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", employeeName=" + employeeName + ", sex=" + sex + ", age=" + age + ", maritalStatus=" + maritalStatus + ", eduation=" + education + ", status=" + status + ", workYears=" + workYears + ", branch=" + branch + ", area=" + area + ", job=" + job + ", workday=" + workday + '}';
    }

    public List<EmployeeSurvey> getEmployeeSurveys() {
        return employeeSurveys;
    }

    public void setEmployeeSurveys(List<EmployeeSurvey> employeeSurveys) {
        this.employeeSurveys = employeeSurveys;
    }

}
