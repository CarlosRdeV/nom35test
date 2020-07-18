/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "section")
public class Section {
    //idSecciones,posicion,idsurvey,inicio,fin,texto,dependiente,pregunta

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idSeccion;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "position")
    private Integer position;

//    private Survey survey;
    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "inicio")
    private Integer inicio;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "fin")
    private Integer fin;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "texto", columnDefinition = "text")
    private String texto;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "dependiente", columnDefinition = "tinyint")
    private Boolean dependiente;

    @NotNull
    @NotEmpty(message = "Not empty")
    @NotBlank(message = "Only chars accepted")
    @Column(name = "question", columnDefinition = "text")
    private String question;

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

//    public Survey getSurvey() {
//        return survey;
//    }
//
//    public void setSurvey(Survey survey) {
//        this.survey = survey;
//    }
    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getDependiente() {
        return dependiente;
    }

    public void setDependiente(Boolean dependiente) {
        this.dependiente = dependiente;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Section() {
    }

    public Section(Integer idSeccion, Integer position, Integer inicio, Integer fin, String texto, Boolean dependiente, String question) {
        this.idSeccion = idSeccion;
        this.position = position;
        this.inicio = inicio;
        this.fin = fin;
        this.texto = texto;
        this.dependiente = dependiente;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Section{" + "idSeccion=" + idSeccion + ", position=" + position + ", inicio=" + inicio + ", fin=" + fin + ", texto=" + texto + ", dependiente=" + dependiente + ", question=" + question + '}';
    }

}
