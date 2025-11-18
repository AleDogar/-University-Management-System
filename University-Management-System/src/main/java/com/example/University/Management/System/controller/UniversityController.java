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
        this.service = service;
    }

    @GetMapping
    public String listUniversities(Model model) {
        model.addAttribute("universities", service.getAllUniversities());
        return "university/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("university", new University());
        return "university/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        University university = service.getUniversityById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university/form";
        }
        return "redirect:/universities";
    }

    @PostMapping("/save")
    public String saveUniversity(@ModelAttribute University university) {
        service.saveUniversity(university);
        return "redirect:/universities";
    }

    @PostMapping("/{id}/delete")
    public String deleteUniversity(@PathVariable String id) {
        service.deleteUniversity(id);
        return "redirect:/universities";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        University university = service.getUniversityById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university/details";
        }
        return "redirect:/universities";
    }
}