// com.example.employeemanagement.service.impl.EmployeeServiceImpl.java

package com.example.employee_management.service.imp;

import com.example.employee_management.model.employee;
import com.example.employee_management.repository.employeeRepository;
import com.example.employee_management.service.employeeService;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class employeeServiceImpl implements employeeService {

    private final employeeRepository employeeRepository;

    public employeeServiceImpl(employeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public employee save(employee employee) {
        if (employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()) {
            String generatedEmployeeId = generateEmployeeId(employee.getDepartment());
            employee.setEmployeeId(generatedEmployeeId);
            System.out.println("Generated Employee ID: " + employee.getEmployeeId());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<employee> findByDepartmentIgnoreCase(String department, Pageable pageable) {
        return employeeRepository.findByDepartmentIgnoreCase(department, pageable);
    }

    @Override
    public Page<employee> getPaginatedEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public long getTaskStatusCount(String status) {
        return employeeRepository.countByStatus(status);
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
                departmentCode = "GEN";
        }

        int randomNumber = (int) (Math.random() * 1000);
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
                return "UNK";
        }
    }
}
