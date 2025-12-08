package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.service.EnrollmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.StudentService;
import com.example.University.Management.System.validation.EnrollmentValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final EnrollmentValidator enrollmentValidator;

    public EnrollmentController(EnrollmentService enrollmentService,
                                CourseService courseService,
                                StudentService studentService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.enrollmentValidator = new EnrollmentValidator();
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("enrollments", enrollmentService.findAll());
        return "enrollment/index";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Enrollment enrollment = enrollmentService.findById(id);
        if (enrollment != null) {
            model.addAttribute("enrollment", enrollment);
            return "enrollment/details";
        }
        return "redirect:/enrollments";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        // Adaugă cursurile și studenții pentru dropdown
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("students", studentService.findAll());
        return "enrollment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Enrollment enrollment = enrollmentService.findById(id);
        if (enrollment != null) {
            model.addAttribute("enrollment", enrollment);
            // Adaugă cursurile și studenții pentru dropdown
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }
        return "redirect:/enrollments";
    }

    @PostMapping("/create")
    public String createEnrollment(@ModelAttribute Enrollment enrollment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {

            enrollmentValidator.validateEnrollment(enrollment);


            Enrollment existingEnrollment = enrollmentService.findById(enrollment.getEnrollmentID());
            if (existingEnrollment != null) {
                throw new RuntimeException("Există deja o înscriere cu acest ID.");
            }


            if (studentService.findById(enrollment.getStudentID()) == null) {
                throw new RuntimeException("Studentul cu ID " + enrollment.getStudentID() + " nu există.");
            }


            if (courseService.findById(enrollment.getCourseID()) == null) {
                throw new RuntimeException("Cursul cu ID " + enrollment.getCourseID() + " nu există.");
            }

            enrollmentService.create(enrollment);
            redirectAttributes.addFlashAttribute("message", "Înscriere creată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("enrollment", enrollment);
            // Reîncarcă datele pentru dropdown
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        return "redirect:/enrollments";
    }

    @PostMapping("/update")
    public String updateEnrollment(@ModelAttribute Enrollment enrollment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {

            enrollmentValidator.validateEnrollment(enrollment);

            Enrollment existingEnrollment = enrollmentService.findById(enrollment.getEnrollmentID());
            if (existingEnrollment == null) {
                throw new RuntimeException("Înscrierea nu există.");
            }

            if (studentService.findById(enrollment.getStudentID()) == null) {
                throw new RuntimeException("Studentul cu ID " + enrollment.getStudentID() + " nu există.");
            }

            if (courseService.findById(enrollment.getCourseID()) == null) {
                throw new RuntimeException("Cursul cu ID " + enrollment.getCourseID() + " nu există.");
            }

            enrollmentService.update(enrollment.getEnrollmentID(), enrollment);
            redirectAttributes.addFlashAttribute("message", "Înscriere actualizată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("enrollment", enrollment);
            // Reîncarcă datele pentru dropdown
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        return "redirect:/enrollments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            enrollmentService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Înscriere ștearsă cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/enrollments";
    }
}