package com.spring.deptempservice.repo;

import com.spring.deptempservice.bean.DeptEmpRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepo extends JpaRepository<DeptEmpRelation, Integer> {
}
