// com.example.employeemanagement.service.EmployeeService.java

package com.example.employee_management.service;

import com.example.employee_management.model.employee;
import java.util.List;

public interface employeeService {
    List<employee> getAllEmployees();
    employee save(employee employee);
    employee getById(Long id);
    void deleteById(Long id);
    
    List<employee> findByDepartmentIgnoreCase(String department);
}
