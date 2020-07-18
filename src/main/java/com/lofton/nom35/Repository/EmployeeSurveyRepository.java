package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Employee;
import com.lofton.nom35.Entity.EmployeeSurvey;
import com.lofton.nom35.Entity.Survey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CGCSTF08
 */
public interface EmployeeSurveyRepository extends JpaRepository<EmployeeSurvey, Integer> {

    public List<EmployeeSurvey> findByEmployee(Employee employee);

    public Optional<EmployeeSurvey> findByEmployeeAndSurvey(Employee employee, Survey survey);

}
