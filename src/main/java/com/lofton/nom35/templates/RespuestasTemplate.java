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
public class RespuestasTemplate {

    private String username;

    private Integer idQuestion;

    private Integer responseValue;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Integer getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(Integer responseValue) {
        this.responseValue = responseValue;
    }

    public RespuestasTemplate() {
    }

    public RespuestasTemplate(String username, Integer idQuestion, Integer responseValue) {
        this.username = username;
        this.idQuestion = idQuestion;
        this.responseValue = responseValue;
    }

    @Override
    public String toString() {
        return "RespuestasTemplate{" + "username=" + username + ", idQuestion=" + idQuestion + ", responseValue=" + responseValue + '}';
    }

}
