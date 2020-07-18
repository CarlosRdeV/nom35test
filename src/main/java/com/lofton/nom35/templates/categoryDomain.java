
package com.lofton.nom35.templates;

import java.util.ArrayList;


public class categoryDomain {
    private int idCategory;
private String nameCategory,riesgo;

private int total;
private ArrayList<domainDimension> domains;

    public categoryDomain(int idCategory, String nameCategory, int total, ArrayList<domainDimension> domains,String riesgo) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.total = total;
        this.domains = domains;
        this.riesgo=riesgo;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<domainDimension> getDomains() {
        return domains;
    }

    public void setDomains(ArrayList<domainDimension> domains) {
        this.domains = domains;
    }
    
}
