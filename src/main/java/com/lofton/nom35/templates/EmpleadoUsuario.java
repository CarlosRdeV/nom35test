/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

import java.io.Serializable;

/**
 *
 * @author CGCSTF08
 */
public class EmpleadoUsuario implements Serializable {

    private String employeeName;

    private String sex;

    private Integer age;

    private String maritalStatus;

    private String education;

    private Integer workYears;

    private String username;

    private String password;

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

    public Integer getWorkYears() {
        return workYears;
    }

    public void setWorkYears(Integer workYears) {
        this.workYears = workYears;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmpleadoUsuario() {
    }

    public EmpleadoUsuario(String employeeName, String sex, Integer age, String maritalStatus, String education, Integer workYears, String username, String password) {
        this.employeeName = employeeName;
        this.sex = sex;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.education = education;
        this.workYears = workYears;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmpleadoUsuario{" + "employeeName=" + employeeName + ", sex=" + sex + ", age=" + age + ", maritalStatus=" + maritalStatus + ", education=" + education + ", workYears=" + workYears + ", username=" + username + ", password=" + password + '}';
    }

}
