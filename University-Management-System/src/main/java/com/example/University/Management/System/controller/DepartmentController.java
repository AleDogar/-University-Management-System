package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService service) { this.service = service; }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("departments", service.getAllDepartments());
        return "department/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department(null, "", null, null, ""));
        return "department/form";
    }

    @PostMapping
    public String add(@ModelAttribute Department d) {
        service.addDepartment(d);
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeDepartment(id);
        return "redirect:/departments";
    }
}