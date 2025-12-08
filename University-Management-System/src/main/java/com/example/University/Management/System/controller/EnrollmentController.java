package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.service.EnrollmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.StudentService;
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

    public EnrollmentController(EnrollmentService enrollmentService,
                                CourseService courseService,
                                StudentService studentService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.studentService = studentService;
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
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("students", studentService.findAll());
        return "enrollment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Enrollment enrollment = enrollmentService.findById(id);
        if (enrollment != null) {
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }
        return "redirect:/enrollments";
    }

    @PostMapping("/create")
    public String createEnrollment(@ModelAttribute Enrollment enrollment, RedirectAttributes redirectAttributes) {
        enrollmentService.create(enrollment);
        redirectAttributes.addFlashAttribute("message", "Înscriere creată cu succes!");
        return "redirect:/enrollments";
    }

    @PostMapping("/update")
    public String updateEnrollment(@ModelAttribute Enrollment enrollment, RedirectAttributes redirectAttributes) {
        enrollmentService.update(enrollment.getEnrollmentID(), enrollment);
        redirectAttributes.addFlashAttribute("message", "Înscriere actualizată cu succes!");
        return "redirect:/enrollments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        enrollmentService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Înscriere ștearsă cu succes!");
        return "redirect:/enrollments";
    }
}
