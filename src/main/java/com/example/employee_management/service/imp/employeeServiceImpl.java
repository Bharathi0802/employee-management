// com.example.employeemanagement.service.impl.EmployeeServiceImpl.java

package com.example.employee_management.service.imp;

import com.example.employee_management.model.employee;
import com.example.employee_management.repository.employeeRepository;
import com.example.employee_management.service.employeeService;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.List;

@Service
public class employeeServiceImpl implements employeeService {

    private final employeeRepository employeeRepository;

    public employeeServiceImpl(employeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public employee save(employee employee) {
    	if (employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()) {
            String generatedEmployeeId = generateEmployeeId(employee.getDepartment());
            employee.setEmployeeId(generatedEmployeeId);  // Set the employeeId
            
            System.out.println("Generated Employee ID: " + employee.getEmployeeId());

        }
        return employeeRepository.save(employee);
    }

    public employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
    
    @Override
    public List<employee> findByDepartmentIgnoreCase(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }
    
 // Method to generate employee ID
    private String generateEmployeeId(String department) {
    	String departmentCode = "";
        switch (department.toLowerCase()) {
            case "production":
                departmentCode = "PRD";
                break;
            case "marketing":
                departmentCode = "MKT";
                break;
            case "hr":
                departmentCode = "HR";
                break;
            case "engineering":
                departmentCode = "ENG";
                break;
            default:
                departmentCode = "GEN"; // Generic code in case department is unknown
        }

        // Generate a random number for uniqueness
        int randomNumber = (int) (Math.random() * 1000);  // Random number between 0 and 999
        return departmentCode + randomNumber;
    }
    
    private String getDepartmentCode(String department) {
        switch (department) {
            case "Production":
                return "PRD";
            case "Marketing":
                return "MKT";
            case "HR":
                return "HR";
            case "Engineering":
                return "ENG";
            default:
                return "UNK"; // Unknown department
        }
    }
}
