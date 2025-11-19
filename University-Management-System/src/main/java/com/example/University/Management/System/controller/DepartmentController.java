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

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("departments", service.getAllDepartments());
        return "department/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Department d = service.getDepartmentById(id);
        if (d != null) {
            model.addAttribute("department", d);
            return "department/form";
        }
        return "redirect:/departments";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Department department) {
        service.saveDepartment(department);
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteDepartment(id);
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Department d = service.getDepartmentById(id);
        if (d != null) {
            model.addAttribute("department", d);
            return "department/details";
        }
        return "redirect:/departments";
    }
}
