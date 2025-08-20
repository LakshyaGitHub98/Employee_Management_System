package com.employee.employee_management_system.models;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String name;
    private String email;
    private String designation;
    private Long salary;
}
