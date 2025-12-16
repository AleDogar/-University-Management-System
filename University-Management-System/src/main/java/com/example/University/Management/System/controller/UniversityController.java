package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    // ================= LISTARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterName,
                          @RequestParam(required = false) String filterCity,
                          Model model) {

        List<University> universities =
                universityService.findAllWithSortAndFilter(sortBy, sortDir, filterName, filterCity);

        model.addAttribute("universities", universities);
        model.addAttribute("sortBy", sortBy != null ? sortBy : "universityID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterName", filterName != null ? filterName : "");
        model.addAttribute("filterCity", filterCity != null ? filterCity : "");

        return "university/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        University university = universityService.findById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university/details";
        }
        redirectAttributes.addFlashAttribute("error", "Universitatea nu există!");
        return "redirect:/universities";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("university", new University());
        model.addAttribute("isEdit", false); // 🔑 FOARTE IMPORTANT
        return "university/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        University university = universityService.findById(id);
        if (university != null) {
            model.addAttribute("university", university);
            model.addAttribute("isEdit", true); // 🔑 FOARTE IMPORTANT
            return "university/form";
        }

        redirectAttributes.addFlashAttribute("error", "Universitatea nu există!");
        return "redirect:/universities";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createUniversity(@Valid @ModelAttribute("university") University university,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", false); // 🔑 rămâne pe CREATE
            return "university/form";
        }

        boolean created = universityService.create(university);
        if (!created) {
            model.addAttribute("error", "ID-ul universității există deja!");
            model.addAttribute("isEdit", false); // 🔑 rămâne pe CREATE
            return "university/form";
        }

        redirectAttributes.addFlashAttribute("message", "Universitate creată cu succes!");
        return "redirect:/universities";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateUniversity(@Valid @ModelAttribute("university") University university,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", true); // 🔑 rămâne pe UPDATE
            return "university/form";
        }

        boolean updated = universityService.update(university.getUniversityID(), university);
        if (!updated) {
            model.addAttribute("error", "Universitatea nu există pentru actualizare!");
            model.addAttribute("isEdit", true);
            return "university/form";
        }

        redirectAttributes.addFlashAttribute("message", "Universitate actualizată cu succes!");
        return "redirect:/universities";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {

        boolean deleted = universityService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Nu se poate șterge universitatea (poate are departamente/săli sau nu există)!"
            );
            return "redirect:/universities";
        }

        redirectAttributes.addFlashAttribute("message", "Universitate ștearsă cu succes!");
        return "redirect:/universities";
    }
}

