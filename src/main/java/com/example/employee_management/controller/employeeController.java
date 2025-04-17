package com.example.employee_management.controller;

import com.example.employee_management.model.employee;
import com.example.employee_management.service.employeeService;
import com.example.employee_management.model.flashMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class employeeController {

    private final employeeService employeeService;

    public employeeController(employeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Display paginated list of all employees
    @GetMapping("/")
    public String listEmployees(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5); // 5 employees per page
        Page<employee> employeePage = employeeService.getPaginatedEmployees(pageable);
        
        model.addAttribute("employeePage", employeePage);
        model.addAttribute("allEmployees", employeePage.getContent());

        // Task status counts
        model.addAttribute("notStartedCount", employeeService.getTaskStatusCount("Not Started"));
        model.addAttribute("inProgressCount", employeeService.getTaskStatusCount("In Progress"));
        model.addAttribute("finishedCount", employeeService.getTaskStatusCount("Finished"));

        return "index";
    }


    // Search by department, paginated
    @GetMapping("/search")
    public String searchByDepartment(@RequestParam String department,
                                     @RequestParam(defaultValue = "0") int page,
                                     Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<employee> employeePage = employeeService.findByDepartmentIgnoreCase(department, pageable);

        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("department", department);

        // Task status counts
        model.addAttribute("notStartedCount", employeeService.getTaskStatusCount("Not Started"));
        model.addAttribute("inProgressCount", employeeService.getTaskStatusCount("In Progress"));
        model.addAttribute("finishedCount", employeeService.getTaskStatusCount("Finished"));

        return "index";
    }

    // Show form to add new employee
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("employee", new employee());
        return "add_employee";
    }

    // Save new employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") employee employee, RedirectAttributes redirectAttributes) {
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("flashMessage", new flashMessage("Employee added successfully!", "success"));
        return "redirect:/";
    }

    // Show form to edit employee
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "edit_employee";
    }

    // Update employee
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") employee employee, RedirectAttributes redirectAttributes) {
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("flashMessage", new flashMessage("Employee updated successfully!", "success"));
        return "redirect:/";
    }

    // Delete employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeService.deleteById(id);
        redirectAttributes.addFlashAttribute("flashMessage", new flashMessage("Employee deleted successfully!", "success"));
        return "redirect:/";
    }
}
