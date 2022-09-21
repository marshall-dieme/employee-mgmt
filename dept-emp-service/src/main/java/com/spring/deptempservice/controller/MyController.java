package com.spring.deptempservice.controller;

import com.spring.deptempservice.bean.DeptEmpRelation;
import com.spring.deptempservice.repo.MyRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relation")
public class MyController {

    private final MyRepo repo;

    public MyController(MyRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/{idDept}/{idEmp}")
    public void create(@PathVariable String idDept,
                       @PathVariable String idEmp) {
        DeptEmpRelation relation = new DeptEmpRelation();
        relation.setDeptId(idDept);
        relation.setEmpId(Long.valueOf(idEmp));
        repo.save(relation);
    }
}
