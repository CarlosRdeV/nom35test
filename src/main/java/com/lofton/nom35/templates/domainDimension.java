
package com.lofton.nom35.templates;

import java.util.ArrayList;

public class domainDimension {
    
private int idDomain;
private int idCategory;
private String nameDomain,riesgo;

   
private int total;
private ArrayList<dimensionResponses> dimensions;

    public domainDimension(int idDomain, int idCategory, String nameDomain, int total, ArrayList<dimensionResponses> dimensions,String riesgo) {
        this.idDomain = idDomain;
        this.idCategory = idCategory;
        this.nameDomain = nameDomain;
        this.total = total;
        this.dimensions = dimensions;
        this.riesgo=riesgo;
    }
 public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }
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

    public String getNameDomain() {
        return nameDomain;
    }

    public void setNameDomain(String nameDomain) {
        this.nameDomain = nameDomain;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<dimensionResponses> getDimensions() {
        return dimensions;
    }

    public void setDimensions(ArrayList<dimensionResponses> dimensions) {
        this.dimensions = dimensions;
    }
    
}
