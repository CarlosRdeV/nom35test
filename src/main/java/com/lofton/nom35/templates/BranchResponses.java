package com.lofton.nom35.templates;


public class BranchResponses {
private int id_employee,id_survey;
double total;
private String employee_name,branch_name,survey_name,enterprise_name,califica;

    public String getCalifica() {
        return califica;
    }

    public void setCalifica(String califica) {
        this.califica = califica;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_survey() {
        return id_survey;
    }

    public void setId_survey(int id_survey) {
        this.id_survey = id_survey;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getSurvey_name() {
        return survey_name;
    }

    public void setSurvey_name(String survey_name) {
        this.survey_name = survey_name;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public BranchResponses(int id_employee, String employee_name, String enterprise_name,  String branch_name, int id_survey, String survey_name, double total,String califica) {
        this.id_employee = id_employee;
        this.employee_name = employee_name;
        this.enterprise_name = enterprise_name;
        this.branch_name = branch_name;
        this.id_survey = id_survey;
        this.survey_name = survey_name;
        this.total = total;
        this.califica=califica;
    }
    
}
