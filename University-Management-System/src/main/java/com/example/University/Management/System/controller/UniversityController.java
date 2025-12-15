package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("error", "Universitatea nu există!");
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
        model.addAttribute("error", "Universitatea nu există!");
        return "redirect:/universities";
    }

    @PostMapping("/create")
    public String createUniversity(@Valid @ModelAttribute("university") University university,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        // Field validation
        if (bindingResult.hasErrors()) {
            return "university/form";
        }

        // Business validation
        boolean created = universityService.create(university);
        if (!created) {
            model.addAttribute("error", "ID-ul universității există deja!");
            return "university/form";
        }

        redirectAttributes.addFlashAttribute("message", "Universitate creată cu succes!");
        return "redirect:/universities";
    }

    @PostMapping("/update")
    public String updateUniversity(@Valid @ModelAttribute("university") University university,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "university/form";
        }

        boolean updated = universityService.update(university.getUniversityID(), university);
        if (!updated) {
            model.addAttribute("error", "Universitatea nu există pentru actualizare!");
            return "university/form";
        }

        redirectAttributes.addFlashAttribute("message", "Universitate actualizată cu succes!");
        return "redirect:/universities";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = universityService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Nu se poate șterge universitatea (poate are departamente/săli sau nu există)!");
            return "redirect:/universities";
        }

        redirectAttributes.addFlashAttribute("message", "Universitate ștearsă cu succes!");
        return "redirect:/universities";
    }
}
