package com.lofton.nom35.Repository;

import com.lofton.nom35.Entity.Branch;
import com.lofton.nom35.Entity.Employee;
import com.lofton.nom35.Entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmployeeNameAndBranchAndWorkYears(String employeeName, Branch branch, Integer workYears);

    Employee findByEmployeeNameAndBranchAndWorkYearsAndAge(String employeeName, Branch branch, Integer workYears, String age);

    List<Employee> findByBranch(Branch theBranch);

    Optional<Employee> findByEmployeeName(String employeeName);

    Optional<Employee> findByUser(User user);
;
}
