package com.employee_frontend.models;

import java.util.Collection;

public class Employee {
    private long id;
    private String name;
    private String email;
    private String designation;
    private Long salary;

    public Employee() {}

    public Employee(String name,String email, String designation, Long salary) {
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.salary = salary;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public long getSalary() { return salary; }
    public void setSalary(long salary) { this.salary = salary; }
}