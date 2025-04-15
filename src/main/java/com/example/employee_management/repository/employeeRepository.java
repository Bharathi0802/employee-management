// com.example.employeemanagement.repository.EmployeeRepository.java

package com.example.employee_management.repository;

import com.example.employee_management.model.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<employee, Long> {
	List<employee> findByDepartmentIgnoreCase(String department);

	boolean existsByEmployeeId(String employeeId);
}
