/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CGCSTF08
 */
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "text",columnDefinition="text")
    private String text;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "valuesQuestion")
    private String valuesQuestion;

    @Column(name = "positionQuestion")
    private Integer positionQuestion;

    @ManyToOne
    @JoinColumn(name = "dimension_id")
    private Dimension dimension;

    @ManyToOne
    @JoinColumn(name = "responseType_id")
    private ResponseType responseType;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "question")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   // @JoinColumn(name = "question_id")
    private List<Response> responses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValuesQuestion() {
        return valuesQuestion;
    }

    public void setValuesQuestion(String valuesQuestion) {
        this.valuesQuestion = valuesQuestion;
    }

    public Integer getPositionQuestion() {
        return positionQuestion;
    }

    public void setPositionQuestion(Integer positionQuestion) {
        this.positionQuestion = positionQuestion;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Question() {
    }

    public Question(String text, String values, Integer position) {
        this.text = text;
        this.valuesQuestion = values;
        this.positionQuestion = position;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", text=" + text + ", values=" + valuesQuestion + ", position=" + positionQuestion + '}';
    }

}
