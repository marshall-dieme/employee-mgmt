package com.spring.employeeservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dept-service", url = "localhost:8700/depts")
public interface DeptProxy {
    @GetMapping("/name/{name}")
    String getIdDept(@PathVariable String name);
}
