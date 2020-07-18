/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

/**
 *
 * @author CGCSTF08
 */
public class EmpleadoPreguntaValor {

    private Integer employeeId;

    private Integer questionId;

    private String value;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EmpleadoPreguntaValor() {
    }

    public EmpleadoPreguntaValor(Integer employeeId, Integer questionId, String value) {
        this.employeeId = employeeId;
        this.questionId = questionId;
        this.value = value;
    }

}
