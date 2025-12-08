package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

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
        Department department = service.findById(id);
        if (department != null) {
            model.addAttribute("department", department);
            return "department/form";
        }
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Department department = service.findById(id);
        if (department != null) {
            model.addAttribute("department", department);
            return "department/details";
        }
        return "redirect:/departments";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute Department department, RedirectAttributes redirectAttributes) {
        service.create(department);
        redirectAttributes.addFlashAttribute("message", "Departament creat cu succes!");
        return "redirect:/departments";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Department department, RedirectAttributes redirectAttributes) {
        service.update(department.getDepartmentID(), department);
        redirectAttributes.addFlashAttribute("message", "Departament actualizat cu succes!");
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Departament șters cu succes!");
        return "redirect:/departments";
    }
}
