// com.example.employeemanagement.repository.EmployeeRepository.java

package com.example.employee_management.repository;

import com.example.employee_management.model.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<employee, Long> {
    List<employee> findByDepartmentIgnoreCase(String department);
    Page<employee> findByDepartmentIgnoreCase(String department, Pageable pageable);
    long countByStatus(String Status);
    boolean existsByEmployeeId(String employeeId);
}
