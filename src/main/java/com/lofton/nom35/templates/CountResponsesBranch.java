
package com.lofton.nom35.templates;

public class CountResponsesBranch {
private String Branch,Survey,riesgo,validator;
private int  respondents,employees,min_respondents,Branch_id,Survey_id;
private double total;

    public CountResponsesBranch(String Branch, String Survey, int respondents, int employees, int min_respondents, double total,String riesgo, String validator, int Survey_id) {
        this.Branch = Branch;
        this.Survey = Survey;
        this.validator=validator;
        this.Survey_id = Survey_id;
        this.respondents = respondents;
        this.employees = employees;
        this.min_respondents = min_respondents;
        this.total=total;
        this.riesgo=riesgo;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public int getBranch_id() {
        return Branch_id;
    }

    public void setBranch_id(int Branch_id) {
        this.Branch_id = Branch_id;
    }

    public int getSurvey_id() {
        return Survey_id;
    }

    public void setSurvey_id(int Survey_id) {
        this.Survey_id = Survey_id;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    public String getSurvey() {
        return Survey;
    }

    public void setSurvey(String Survey) {
        this.Survey = Survey;
    }

    public int getRespondents() {
        return respondents;
    }

    public void setRespondents(int respondents) {
        this.respondents = respondents;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public int getMin_respondents() {
        return min_respondents;
    }

    public void setMin_respondents(int min_respondents) {
        this.min_respondents = min_respondents;
    }

}
