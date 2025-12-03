
package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.service.UniversityService;
import com.example.University.Management.System.validation.UniversityValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;
    private final UniversityValidator universityValidator;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
        this.universityValidator = new UniversityValidator();
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
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {
            // 1. Validare câmpuri
            universityValidator.validateUniversity(university);

            // 2. Verifică dacă ID-ul există deja
            University existingUniversity = universityService.findById(university.getUniversityID());
            if (existingUniversity != null) {
                throw new RuntimeException("Există deja o universitate cu acest ID.");
            }

            // 3. Creează universitate nouă
            universityService.create(university);
            redirectAttributes.addFlashAttribute("message", "Universitate creată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("university", university);
            return "university/form";
        }

        return "redirect:/universities";
    }

    @PostMapping("/update")
    public String updateUniversity(@ModelAttribute University university,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {
            // 1. Validare câmpuri
            universityValidator.validateUniversity(university);

            // 2. Verifică că universitatea există
            University existingUniversity = universityService.findById(university.getUniversityID());
            if (existingUniversity == null) {
                throw new RuntimeException("Universitatea nu există.");
            }

            // 3. Face update
            universityService.update(university.getUniversityID(), university);
            redirectAttributes.addFlashAttribute("message", "Universitate actualizată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("university", university);
            return "university/form";
        }

        return "redirect:/universities";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            // Verifică dacă există departamente sau săli asociate
            University university = universityService.findById(id);
            if (university != null &&
                    (!university.getDepartments().isEmpty() || !university.getRooms().isEmpty())) {
                throw new RuntimeException("Nu poți șterge universitatea deoarece are departamente sau săli asociate!");
            }

            universityService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Universitate ștearsă cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/universities";
    }
}