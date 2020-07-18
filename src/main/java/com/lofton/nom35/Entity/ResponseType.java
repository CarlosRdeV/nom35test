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
@Table(name = "responseType")
public class ResponseType {

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
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "response")
    private String response;

    @OneToMany(mappedBy = "responseType")
    private List<Question> questions;

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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public ResponseType() {
    }

    public ResponseType(String name, String response) {
        this.name = name;
        this.response = response;
    }

    @Override
    public String toString() {
        return "ResponseType{" + "id=" + id + ", name=" + name + ", response=" + response + '}';
    }

    public void addQuestions(Question theQuestion) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(theQuestion);
    }

}
