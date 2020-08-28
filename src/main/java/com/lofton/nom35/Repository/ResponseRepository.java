/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Response;
import com.lofton.nom35.templates.CountResponsesBranch;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author CGCSTF08
 */
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    Response findByValue(String value);
   
  @Query(value = "Select response.* from response,question where employee_id = ?1 and response.question_id=question.id and question.survey_id = ?2 order by question.position_question ", nativeQuery = true)
  List<Response> findByEmployeeId(@Param("employe_id") int employeId, @Param("survey_id") int surveyId );
  
  @Query(value = "Select response.id,  sum(value)/count(distinct employee_id) as value, employee_id, question_id from response,question where employee_id in (select id_employee from employee, branch where employee.branch_id = branch.id_branch and branch.validator=?1 ) and response.question_id=question.id and question.survey_id = ?2 group by question_id  order by question.position_question", nativeQuery = true)
  List<Response> branchTotals(@Param("validator") String validator, @Param("survey_id") int surveyId );
  
  @Query(value = "Select response.id,  sum(value)/count(distinct employee_id) as value, employee_id, question_id from response,question where employee_id in (select id_employee from employee, branch where employee.branch_id = branch.id_branch and branch.validator in (Select validator from branch where enterprise_id = ?1)) and response.question_id=question.id and question.survey_id = ?2 group by question_id  order by question.position_question", nativeQuery = true)
  List<Response> EnterpriseTotals(@Param("enterprise_id") int enterprise_id, @Param("survey_id") int surveyId );
  
  @Query(value = "Select employee.id_employee , employee.employee_name as nombre ,enterprise.name as empresa, branch.name as centro, survey_id,survey.name as Encuesta ,sum(response.value) as total, survey.rangos from response,question,employee,survey,branch,enterprise where employee_id = employee.id_employee and response.question_id=question.id and question.survey_id = survey.id and survey.id != 1 and employee.branch_id = branch.id_branch and branch.enterprise_id = enterprise.id_enterprise and branch.validator = ?1 GROUP by survey_id, employee.id_employee", nativeQuery = true)
  List<Object[]> dataSurveyBranch(@Param("validator") String validator);
  
  @Query(value = "Select id_employee, nombre, empresa, centro, survey_id, Encuesta,if(sectionI=10, if((sectionII>=10  or sectionII >=30 or sectionIV >=20),1,0),0) as total, 'N/A' as rangos  from Tipo_1 where validator = ?1 ", nativeQuery = true)
  List<Object[]> dataSurveyBranchType1(@Param("validator") String validator);
  
  
  @Query(value = "SELECT x.name,x.surveyName ,count(DISTINCT(a.employee_id)) as respondents,x.employees, x.min_respondents, ifnull(sum(a.value)/count(distinct a.employee_id),0) as value, rangos, x.validator,x.survey_id FROM (select branch.*, survey_id, survey.name as surveyName, survey.rangos  from branch, branch_has_survey, survey where enterprise_id= ?1 and branch.id_branch=branch_has_survey.branch_id and branch_has_survey.survey_id=survey.id  and survey.id!=1) as x left join (select response.*,employee.*,survey_id from employee,response,question where employee.id_employee=response.employee_id and response.question_id = question.id ) a on a.branch_id = x.id_branch and x.survey_id=a.survey_id GROUP by x.id_branch,x.survey_id", nativeQuery = true)
 List<Object[]> enterpriseCountsCenter(@Param("enterprise_id") int enterpriseId);
  
 
 @Query(value = "SELECT x.name,x.surveyName ,count(DISTINCT(a.employee_id)) as respondents,x.employees, x.min_respondents, ifnull(sum(a.value)/count(distinct a.employee_id),0) as value, rangos, x.validator,x.survey_id FROM (select branch.*, survey_id, survey.name as surveyName, survey.rangos  from branch, branch_has_survey, survey where validator= ?1 and branch.id_branch=branch_has_survey.branch_id and branch_has_survey.survey_id=survey.id  and survey.id!=1) as x left join (select response.*,employee.*,survey_id from employee,response,question where employee.id_employee=response.employee_id and response.question_id = question.id ) a on a.branch_id = x.id_branch and x.survey_id=a.survey_id GROUP by x.id_branch,x.survey_id", nativeQuery = true)
 List<Object[]> BranchCounts(@Param("validator") String validator);
  
 @Query(value = "SELECT centro as name, Encuesta as surveyName, count(id_employee)as respondents,(select (employees) from branch where validator=Tipo_1.validator) as employees, (select (min_respondents) from branch where validator=Tipo_1.validator) as min_respondents,(select count(id_employee) FROM Tipo_1 u where sectionI=10 and (sectionII>=10 or sectionII >=30 or sectionIV >=20) and validator=Tipo_1.validator) as value, \"N/A\" as rangos,  validator, survey_id FROM Tipo_1 where  Tipo_1.validator = ?1 group by validator", nativeQuery = true)
 List<Object[]> BranchCountsType1(@Param("validator") String validator);
 
 @Query(value = "SELECT centro as name, Encuesta as surveyName, count(id_employee)as respondents,(select (employees) from branch where validator=Tipo_1.validator) as employees, (select (min_respondents) from branch where validator=Tipo_1.validator) as min_respondents,(select count(id_employee) FROM Tipo_1 u where sectionI=10 and (sectionII>=10 or sectionII >=30 or sectionIV >=20) and validator=Tipo_1.validator) as value, \"N/A\" as rangos,  validator, survey_id FROM Tipo_1 where  Tipo_1.enterprise_id = ?1 group by validator", nativeQuery = true)
 List<Object[]> enterpriseCenterType1(@Param("enterprise_id") int enterpriseId);
  
 @Query(value = "SELECT x.enterpriseName as name,x.surveyName ,count(DISTINCT(a.employee_id)) as respondents,sum(DISTINCT(x.employees)) as employees, sum(DISTINCT(x.min_respondents)) as min_respondents, ifnull(sum(a.value)/count(distinct a.employee_id),0) as value, rangos, 'N/A' as validator ,x.survey_id FROM (select branch.*,enterprise.name as enterpriseName, survey_id, survey.name as surveyName, survey.rangos  from branch, branch_has_survey, survey,enterprise where enterprise.id_enterprise = enterprise_id and enterprise_id= ?1 and branch.id_branch=branch_has_survey.branch_id and branch_has_survey.survey_id=survey.id and survey.id!=1) as x left join (select response.*,employee.*,survey_id from employee,response,question where employee.id_employee=response.employee_id and response.question_id = question.id ) a on a.branch_id = x.id_branch and x.survey_id=a.survey_id GROUP by x.survey_id", nativeQuery = true)
 List<Object[]> enterpriseCounts(@Param("enterprise_id") int enterpriseId);

 @Query(value = "SELECT enterprise.name, survey.name as surveyName, ifnull((select count(id_employee) from Tipo_1 where enterprise_id= enterprise.id_enterprise),0)as respondents,ifnull((select sum(employees) from branch where enterprise_id=enterprise.id_enterprise),0) as employees, ifnull((select sum(min_respondents) from branch where enterprise_id=enterprise.id_enterprise),0) as min_respondents,(select count(id_employee) FROM Tipo_1 as x where sectionI=10 and (sectionII>=10 or sectionII >=30 or sectionIV >=20) and enterprise_id=enterprise.id_enterprise) as value, \"N/A\" as rangos, \"N/A\" as validator, 1 as survey_id FROM enterprise,survey where enterprise.id_enterprise =?1 and survey.id = 1", nativeQuery = true)
 List<Object[]> enterpriseType1(@Param("enterprise_id") int enterpriseId);
}
