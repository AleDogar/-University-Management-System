package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.service.EnrollmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final StudentService studentService;

    public EnrollmentController(EnrollmentService enrollmentService,
                                CourseService courseService,
                                StudentService studentService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    // Listare înscrieri
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("enrollments", enrollmentService.findAll());
        return "enrollment/index";
    }

    // Detalii înscriere
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Enrollment enrollment = enrollmentService.findById(id);
        if (enrollment != null) {
            model.addAttribute("enrollment", enrollment);
            return "enrollment/details";
        }
        redirectAttributes.addFlashAttribute("error", "Înscrierea nu există!");
        return "redirect:/enrollments";
    }

    // Formular adăugare înscriere
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("students", studentService.findAll());
        return "enrollment/form";
    }

    // Formular editare înscriere
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Enrollment enrollment = enrollmentService.findById(id);
        if (enrollment != null) {
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }
        redirectAttributes.addFlashAttribute("error", "Înscrierea nu există!");
        return "redirect:/enrollments";
    }

    // Creare înscriere
    @PostMapping("/create")
    public String createEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        // Verificăm mai întâi erorile de validare din model
        if (bindingResult.hasErrors()) {
            // Reîncărcăm dropdown-urile pentru formular
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        // Business validation 1: Verificăm dacă ID-ul există deja
        if (enrollmentService.findById(enrollment.getEnrollmentID()) != null) {
            model.addAttribute("error", "ID-ul înscrierii '" + enrollment.getEnrollmentID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        // Business validation 2: Verificăm dacă student-ul există
        if (!enrollmentService.studentExists(enrollment.getStudentID())) {
            model.addAttribute("error", "Student-ul cu ID-ul '" + enrollment.getStudentID() + "' nu există!");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        // Business validation 3: Verificăm dacă cursul există
        if (!enrollmentService.courseExists(enrollment.getCourseID())) {
            model.addAttribute("error", "Cursul cu ID-ul '" + enrollment.getCourseID() + "' nu există!");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        // Dacă toate validările au trecut, creăm înscrierea
        boolean created = enrollmentService.create(enrollment);
        if (!created) {
            model.addAttribute("error", "Eroare la crearea înscrierii! Verificați datele introduse.");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        redirectAttributes.addFlashAttribute("message", "Înscriere creată cu succes!");
        return "redirect:/enrollments";
    }

    // Actualizare înscriere
    @PostMapping("/update")
    public String updateEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        // Verificăm erorile de validare
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        // Business validation: Verificăm dacă student-ul există
        if (!enrollmentService.studentExists(enrollment.getStudentID())) {
            model.addAttribute("error", "Student-ul cu ID-ul '" + enrollment.getStudentID() + "' nu există!");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        // Business validation: Verificăm dacă cursul există
        if (!enrollmentService.courseExists(enrollment.getCourseID())) {
            model.addAttribute("error", "Cursul cu ID-ul '" + enrollment.getCourseID() + "' nu există!");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        boolean updated = enrollmentService.update(enrollment.getEnrollmentID(), enrollment);
        if (!updated) {
            model.addAttribute("error", "Înscrierea nu există pentru actualizare!");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        redirectAttributes.addFlashAttribute("message", "Înscriere actualizată cu succes!");
        return "redirect:/enrollments";
    }

    // Ștergere înscriere
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = enrollmentService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Nu se poate șterge înscrierea (nu există)!");
            return "redirect:/enrollments";
        }

        redirectAttributes.addFlashAttribute("message", "Înscriere ștearsă cu succes!");
        return "redirect:/enrollments";
    }
}