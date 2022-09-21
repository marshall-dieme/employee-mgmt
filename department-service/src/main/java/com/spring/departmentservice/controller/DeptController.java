package com.spring.departmentservice.controller;

import com.spring.departmentservice.bean.Department;
import com.spring.departmentservice.services.DeptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/depts")
public class DeptController {
    private final DeptService service;

    public DeptController(DeptService service) {
        this.service = service;
    }

    @GetMapping
    public List<Department> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Department> getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Department create(@RequestBody Department dept) {
        return service.create(dept);
    }

    @PutMapping
    public Department update(@RequestBody Department dept) {
        return service.update(dept);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/name/{name}")
    public String getIdDept(@PathVariable String name) {
        return service.getIdDept(name);
    }
}
