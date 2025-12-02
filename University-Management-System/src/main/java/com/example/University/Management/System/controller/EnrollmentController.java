package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.ClassGrade;
import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    @Autowired
    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("enrollments", service.findAll());
        model.addAttribute("grades", ClassGrade.values());
        return "enrollment/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("grades", ClassGrade.values());
        return "enrollment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Enrollment e = service.findById(id);
        if (e != null) {
            model.addAttribute("enrollment", e);
            model.addAttribute("grades", ClassGrade.values());
            return "enrollment/form";
        }
        return "redirect:/enrollments";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Enrollment e) {
        service.create(e);
        return "redirect:/enrollments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/enrollments";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Enrollment e = service.findById(id);
        if (e != null) {
            model.addAttribute("enrollment", e);
            return "enrollment/details";
        }
        return "redirect:/enrollments";
    }
}
