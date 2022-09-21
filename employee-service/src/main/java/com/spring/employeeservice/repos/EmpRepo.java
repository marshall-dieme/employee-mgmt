package com.spring.employeeservice.repos;

import com.spring.employeeservice.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByIdDept(String idDept);
}
