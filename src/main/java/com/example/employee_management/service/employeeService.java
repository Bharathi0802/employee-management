// com.example.employeemanagement.service.EmployeeService.java
package com.example.employee_management.service;

import com.example.employee_management.model.employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface employeeService {
    List<employee> getAllEmployees();  // Get all employees without pagination
    employee save(employee employee);   // Save employee
    employee getById(Long id);          // Get employee by ID
    void deleteById(Long id);           // Delete employee by ID
    Page<employee> getPaginatedEmployees(Pageable pageable); // Pagination for all employees
    Page<employee> findByDepartmentIgnoreCase(String department, Pageable pageable); // Pagination for department search
    long getTaskStatusCount(String status); //task count for pie chart
}
