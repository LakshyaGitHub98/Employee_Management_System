package com.employee.employee_management_system.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employee_management_system.DAO.EmployeeEntity;
import com.employee.employee_management_system.models.Employee;
import com.employee.employee_management_system.repository.EmployeesRepo;


@Service
public class EmployeesServicesImp implements EmployeesServices {

    @Autowired
    private EmployeesRepo employeesRepository;

    @Override
    public List<Employee> getEmployees() {
        List<EmployeeEntity> employees = employeesRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        // Logic to get employees will go here
        if (employees.isEmpty()) {
            System.out.println("----------------------------");
            System.out.println("No employees found");
            System.out.println("----------------------------");
            return employeeList; // Return empty list if no employees found
        }
        // System.out.println("This is the list of employees");
        for (EmployeeEntity entity : employees) {
            Employee emp = new Employee();
            BeanUtils.copyProperties(entity, emp);
            employeeList.add(emp);
        }
   
        return employeeList;
    }

    @Override
    public String createEmployee(Employee entity) {
        if (entity == null || entity.getName() == null || entity.getName().isEmpty() )  {
            return "Invalid employee data provided";
        }
        if(entity.getSalary() == null || entity.getSalary() <= 0) {
            return "Invalid salary provided";
        }
        
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(entity, employeeEntity);
        employeesRepository.save(employeeEntity);
        return "Employee created successfully";
    }

    @Override
    public String updateEmployee(Long id, Employee entity) {
        System.out.println("Main chla bhai");
        
        if (id == null || entity == null) {
            return "Invalid employee ID or data provided";
        }
        EmployeeEntity existingEmployee = employeesRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            return "Employee with ID " + id + " not found";
        }
        BeanUtils.copyProperties(entity, existingEmployee,"id");
        // entity.setName(existingEmployee.getName());
        // entity.setEmail(existingEmployee.getEmail());
        // entity.setDesignation(existingEmployee.getDesignation());
        // entity.setSalary(existingEmployee.getSalary());

        employeesRepository.save(existingEmployee);
        return "Employee with ID " + id + " updated successfully";
    }

    @Override
    public String deleteEmployee(Long id) {
        if(id == null) {
            return "Invalid employee ID provided";
        }

        if (!employeesRepository.existsById(id)) {
            return "Employee with ID " + id + " not found";
        }

        employeesRepository.deleteById(id);
        return "Employee with ID " + id + " deleted successfully";
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeesRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            return null; // Employee not found
        } 
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee; // Return the found employee
    }
}
