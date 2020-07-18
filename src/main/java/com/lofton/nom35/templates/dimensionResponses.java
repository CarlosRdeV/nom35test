
package com.lofton.nom35.templates;

import java.util.ArrayList;
import java.util.List;

public class dimensionResponses {
private int idDimension;
private int idDomain;
private int idCategory;
private String nameDimension;
private int total;
private ArrayList<ResponseComplete> responses;

    public int getIdDomain() {
        return idDomain;
    }

    public void setIdDomain(int idDomain) {
        this.idDomain = idDomain;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }


    public ArrayList<ResponseComplete> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<ResponseComplete> responses) {
        this.responses = responses;
    }

    public int getIdDimension() {
        return idDimension;
    }

    public void setIdDimension(int idDimension) {
        this.idDimension = idDimension;
    }

    public String getNameDimension() {
        return nameDimension;
    }

    public void setNameDimension(String nameDimension) {
        this.nameDimension = nameDimension;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public dimensionResponses(int idDimension, String nameDimension, int total, ArrayList<ResponseComplete> responses) {
        this.idDimension = idDimension;
        this.nameDimension = nameDimension;
        this.total = total;
        this.responses = responses;
    }

    public dimensionResponses( int idCategory,  int idDomain,int idDimension, String nameDimension, int total, ArrayList<ResponseComplete> responses) {
        this.idDimension = idDimension;
        this.idDomain = idDomain;
        this.idCategory = idCategory;
        this.nameDimension = nameDimension;
        this.total = total;
        this.responses = responses;
    }

  
       
}
