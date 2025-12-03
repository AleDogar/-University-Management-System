package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.service.EnrollmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.validation.EnrollmentValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;
    private final CourseService courseService;

    public EnrollmentController(EnrollmentService service, CourseService courseService) {
        this.service = service;
        this.courseService = courseService;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("enrollments", service.findAll());
        return "enrollment/index";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Enrollment enrollment = service.findById(id);
        if (enrollment != null) {
            model.addAttribute("enrollment", enrollment);
            return "enrollment/details";
        }
        return "redirect:/enrollments";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        // Adaugă cursurile disponibile pentru dropdown
        model.addAttribute("courses", courseService.findAll());
        return "enrollment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Enrollment enrollment = service.findById(id);
        if (enrollment != null) {
            model.addAttribute("enrollment", enrollment);
            // Adaugă cursurile disponibile pentru dropdown
            model.addAttribute("courses", courseService.findAll());
            return "enrollment/form";
        }
        return "redirect:/enrollments";
    }

    // CREATE (înscriere nouă)
    @PostMapping("/create")
    public String createEnrollment(@ModelAttribute Enrollment enrollment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {
            // 1. Validare câmpuri
            EnrollmentValidator.validateEnrollment(enrollment);

            // 2. Verifică dacă ID-ul există deja
            Enrollment existingEnrollment = service.findById(enrollment.getEnrollmentID());
            if (existingEnrollment != null) {
                throw new RuntimeException("Există deja o înscriere cu acest ID.");
            }

            // 3. Verifică dacă Course ID există în baza de date
            if (courseService.findById(enrollment.getCourseID()) == null) {
                throw new RuntimeException("Cursul cu ID " + enrollment.getCourseID() + " nu există.");
            }

            // 4. Creează înscriere nouă
            service.create(enrollment);
            redirectAttributes.addFlashAttribute("message", "Înscriere creată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("enrollment", enrollment);
            // Reîncarcă cursurile pentru dropdown
            model.addAttribute("courses", courseService.findAll());
            return "enrollment/form";
        }

        return "redirect:/enrollments";
    }

    // UPDATE (editare)
    @PostMapping("/update")
    public String updateEnrollment(@ModelAttribute Enrollment enrollment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {
            // 1. Validare câmpuri
            EnrollmentValidator.validateEnrollment(enrollment);

            // 2. Verifică că înscrierea există
            Enrollment existingEnrollment = service.findById(enrollment.getEnrollmentID());
            if (existingEnrollment == null) {
                throw new RuntimeException("Înscrierea nu există.");
            }

            // 3. Verifică dacă Course ID există în baza de date
            if (courseService.findById(enrollment.getCourseID()) == null) {
                throw new RuntimeException("Cursul cu ID " + enrollment.getCourseID() + " nu există.");
            }

            // 4. Face update
            service.update(enrollment.getEnrollmentID(), enrollment);
            redirectAttributes.addFlashAttribute("message", "Înscriere actualizată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("enrollment", enrollment);
            // Reîncarcă cursurile pentru dropdown
            model.addAttribute("courses", courseService.findAll());
            return "enrollment/form";
        }

        return "redirect:/enrollments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "Înscriere ștearsă cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/enrollments";
    }
}