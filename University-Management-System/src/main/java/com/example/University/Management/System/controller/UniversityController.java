package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.service.UniversityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("universities", universityService.findAll());
        return "university/index";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        University university = universityService.findById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university/details";
        }
        return "redirect:/universities";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("university", new University());
        return "university/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        University university = universityService.findById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university/form";
        }
        return "redirect:/universities";
    }

    @PostMapping("/create")
    public String createUniversity(@ModelAttribute University university,
                                   RedirectAttributes redirectAttributes) {
        universityService.create(university);
        redirectAttributes.addFlashAttribute("message", "Universitate creată cu succes!");
        return "redirect:/universities";
    }

    @PostMapping("/update")
    public String updateUniversity(@ModelAttribute University university,
                                   RedirectAttributes redirectAttributes) {
        universityService.update(university.getUniversityID(), university);
        redirectAttributes.addFlashAttribute("message", "Universitate actualizată cu succes!");
        return "redirect:/universities";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        universityService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Universitate ștearsă cu succes!");
        return "redirect:/universities";
    }
}
