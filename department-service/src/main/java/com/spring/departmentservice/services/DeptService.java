package com.spring.departmentservice.services;

import com.spring.departmentservice.bean.Department;
import com.spring.departmentservice.repos.DeptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptService {

    //@Autowired
    private final DeptRepository repo;

    public DeptService(DeptRepository repo) {
        this.repo = repo;
    }

    public List<Department> getAll(){
        return repo.findAll();
    }

    public Optional<Department> getById(String id) {
        return repo.findById(id);
    }

    public Department create(Department dept) {
        dept.generateId();
        return repo.save(dept);
    }

    public Department update(Department dept) {
        return repo.save(dept);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public String getIdDept(String name) {
        return repo.getIdDept(name);
    }
}
