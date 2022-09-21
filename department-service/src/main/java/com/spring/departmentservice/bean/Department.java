package com.spring.departmentservice.bean;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Transient
    private static int cpt = 0;

    @Id
    private String deptId;

    @Column(unique = true)
    private String name;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void generateId() {
        this.deptId = "DEPT_" + ++cpt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
