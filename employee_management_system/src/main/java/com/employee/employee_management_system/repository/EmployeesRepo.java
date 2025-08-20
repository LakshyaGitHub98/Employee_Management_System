package com.employee.employee_management_system.repository;

import com.employee.employee_management_system.DAO.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeesRepo extends JpaRepository<EmployeeEntity, Long> {
    
}
