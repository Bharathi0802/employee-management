// com.example.employeemanagement.controller.EmployeeController.java

package com.example.employee_management.controller;

import com.example.employee_management.model.employee;
import com.example.employee_management.service.employeeService;
import com.example.employee_management.model.flashMessage;
import java.util.List;

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

    @GetMapping("/")
    public String listEmployees(Model model) {
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        return "index";
    }

    // Search by department
    @GetMapping("/search")
    public String searchEmployees(@RequestParam("department") String department, Model model, flashMessage flashMessage) {
        List<employee> employees = employeeService.findByDepartmentIgnoreCase(department);
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        model.addAttribute("flashMessage", flashMessage); 
        model.addAttribute("employees", employees);
        model.addAttribute("department", department);
        return "index";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("employee", new employee());
        return "add_employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") employee employee, RedirectAttributes redirectAttributes) {
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("flashMessage", new flashMessage("Employee added successfully!", "success"));
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "edit_employee";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") employee employee, RedirectAttributes redirectAttributes) {
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("flashMessage", new flashMessage("Employee updated successfully!", "success"));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeService.deleteById(id);
        redirectAttributes.addFlashAttribute("flashMessage", new flashMessage("Employee deleted successfully!", "success"));
        return "redirect:/";
    }
}

