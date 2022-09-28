package com.spring.employeeservice.controller;

import com.spring.employeeservice.bean.Employee;
import com.spring.employeeservice.bean.EmployeeDTO;
import com.spring.employeeservice.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")

public class EmpController {

    private final Logger LOGGER = LoggerFactory.getLogger(EmpController.class);
    private final EmpService service;

    public EmpController(EmpService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll(){
        return service.getAll();
    }

    @GetMapping("/{name}/employees")
    public List<Employee> getEmpByDept(@PathVariable String name) {
        return service.getByDept(name);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Employee create(@RequestBody EmployeeDTO emp) {
        LOGGER.info("CREATING NEW EMPLOYEE...");
        return service.create(emp);
    }

    @PostMapping("/create/feign")
    public Employee createWithFeignClient(@RequestBody EmployeeDTO emp) {
        LOGGER.info("CREATING NEW EMPLOYEE...");
        return service.createWithFeign(emp);
    }

    @PostMapping("/create")
    public Employee createWithRelation(@RequestBody EmployeeDTO emp) {
        return service.createWithRelation(emp);
    }

    @PutMapping
    public Employee update(@RequestBody EmployeeDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }



}
