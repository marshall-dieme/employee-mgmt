package com.spring.employeeservice.service;

import com.spring.employeeservice.bean.Employee;
import com.spring.employeeservice.bean.EmployeeDTO;
import com.spring.employeeservice.repos.EmpRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpService {
    private final EmpRepo repo;

    public EmpService(EmpRepo repo) {
        this.repo = repo;
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Optional<Employee> getById(Long id) {
        return repo.findById(id);
    }

    public Employee create(EmployeeDTO dto) {
        Employee employee = new Employee();
        RestTemplate template = new RestTemplate();
        Map<String, String> urlValues = new HashMap<>();
        urlValues.put("name", dto.getDept());
        String idDept = template.getForEntity(
                "http://localhost:8700/depts/name/{name}",
                        String.class,
                        urlValues)
                        .getBody();
        changeToModel(dto, employee);
        employee.setIdDept(idDept);
        return repo.save(employee);
    }

    public Employee update(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        changeToModel(dto, employee);
        return repo.save(employee);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private static void changeToModel(EmployeeDTO dto, Employee employee) {
        employee.setFullName(dto.getFullName());
        employee.setJob(dto.getJob());
        employee.setSalary(dto.getSalary());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate hireDate = LocalDate.parse(dto.getHireDate(), format);
        employee.setHireDate(hireDate);
    }

    public List<Employee> getByDept(String name) {
        Map<String, String> values = new HashMap<>();
        values.put("name", name);
        RestTemplate template = new RestTemplate();
        String idDept = template.getForEntity("http://localhost:8700/depts/name/{name}",
                String.class, values).getBody();

        return repo.findByIdDept(idDept);
    }

    public Employee createWithRelation(EmployeeDTO dto) {
        Employee emp = new Employee();
        changeToModel(dto, emp);
        String idDept = new RestTemplate().getForEntity(
                "http://localhost:8700/depts/name/"+dto.getDept(),
                String.class).getBody();
        emp = repo.save(emp);
        Map<String, String> values = new HashMap<>();
        values.put("idDept", idDept);
        values.put("idEmp", String.valueOf(emp.getId()));
        new RestTemplate().getForEntity("http://localhost:8900/relation/{idDept}/{idEmp}",
                Void.class, values);

        return emp;
    }
}
