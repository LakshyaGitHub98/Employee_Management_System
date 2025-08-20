package com.employee.employee_management_system.Services;

import java.util.List;

import com.employee.employee_management_system.models.Employee;

public interface EmployeesServices {

    List<Employee> getEmployees();
    Employee getEmployeeById(Long id);
    String createEmployee(Employee entity);
    String updateEmployee(Long id, Employee entity);
    String deleteEmployee(Long id);

    
}