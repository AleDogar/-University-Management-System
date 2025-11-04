package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.service.UniversityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/universities")
public class UniversityController {
    private final UniversityService service;
    public UniversityController(UniversityService service) {
        this.service = service; }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("universities", service.getAllUniversities());
        return "university/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("university", new University(null, "", ""));
        return "university/form";
    }

    @PostMapping
    public String add(@ModelAttribute University u) {
        service.addUniversity(u);
        return "redirect:/universities";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeUniversity(id);
        return "redirect:/universities";
    }
}
