package com.spring.departmentservice.repos;

import com.spring.departmentservice.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Department, String> {

   // @Query(value = "select dept_id from departments where name=?1", nativeQuery = true)
    @Query("select d.deptId from Department d where d.name=:name")
    String getIdDept(String name);

    Department findByName(String name);
}
