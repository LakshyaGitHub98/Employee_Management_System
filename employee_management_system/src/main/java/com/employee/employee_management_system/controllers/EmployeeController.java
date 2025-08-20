package com.employee.employee_management_system.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee_management_system.Services.EmployeesServicesImp;
import com.employee.employee_management_system.models.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RequestMapping("/employees")
@RestController
public class EmployeeController {
    

    @Autowired
    private EmployeesServicesImp employeesServices;


    @GetMapping("/read")
    public List<Employee> getEmployees() {
        return employeesServices.getEmployees();
    }

    @GetMapping("/read/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeesServices.getEmployeeById(id);
    }

    @PostMapping("/create")
    public String postMethodName(@RequestBody Employee entity) {
        System.out.println("Designation received: " + entity.getDesignation());
        return employeesServices.createEmployee(entity);
    }

    @PutMapping("/update/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee entity) {
        return employeesServices.updateEmployee(id, entity);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMethodName(@PathVariable Long id) {
        return employeesServices.deleteEmployee(id);
    }
}
