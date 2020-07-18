/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

public class ResponseComplete {
    private int dimension;
    private int position;
    private String value;
    private String pregunta;

  

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPregunta() {
        return pregunta;
    }



       public ResponseComplete(int dimension, int position, String value, String pregunta) {
       
        this.dimension = dimension;
        this.position = position;
        this.value = value;
        this.pregunta = pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
}
