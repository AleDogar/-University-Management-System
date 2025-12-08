package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.service.DepartmentService;
import com.example.University.Management.System.validation.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("departments", service.findAll());
        return "department/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Department d = service.findById(id);
        if (d != null) {
            model.addAttribute("department", d);
            return "department/form";
        }
        return "redirect:/departments";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute Department department,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {

            DepartmentValidator.validateDepartment(department);

            Department existingDepartment = service.findById(department.getDepartmentID());
            if (existingDepartment != null) {
                throw new RuntimeException("Există deja un departament cu acest ID.");
            }

            service.create(department);
            redirectAttributes.addFlashAttribute("message", "Departament creat cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("department", department);
            return "department/form";
        }

        return "redirect:/departments";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Department department,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {

            DepartmentValidator.validateDepartment(department);

            Department existingDepartment = service.findById(department.getDepartmentID());
            if (existingDepartment == null) {
                throw new RuntimeException("Departamentul nu există.");
            }

            service.update(department.getDepartmentID(), department);
            redirectAttributes.addFlashAttribute("message", "Departament actualizat cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("department", department);
            return "department/form";
        }

        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "Departament șters cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Department d = service.findById(id);
        if (d != null) {
            model.addAttribute("department", d);
            return "department/details";
        }
        return "redirect:/departments";
    }
}