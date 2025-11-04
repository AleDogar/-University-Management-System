package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.model.ClassGrade;
import com.example.University.Management.System.service.EnrollmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("enrollments", service.getAllEnrollments());
        model.addAttribute("grades", ClassGrade.values());
        return "enrollment/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new Enrollment(null, "", ClassGrade.NA));
        model.addAttribute("grades", ClassGrade.values());
        return "enrollment/form";
    }

    @PostMapping
    public String add(@ModelAttribute Enrollment e) {
        service.addEnrollment(e);
        return "redirect:/enrollments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeEnrollment(id);
        return "redirect:/enrollments";
    }
}