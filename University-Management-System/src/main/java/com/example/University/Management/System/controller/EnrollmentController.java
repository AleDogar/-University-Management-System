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

import java.util.List;
import java.util.Map;

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

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterEnrollmentID,
                          @RequestParam(required = false) String filterStudentID,
                          @RequestParam(required = false) String filterCourseID,
                          @RequestParam(required = false) String filterGrade,
                          Model model) {

        List<Enrollment> enrollments = enrollmentService.findAllWithSortAndFilter(
                sortBy, sortDir, filterEnrollmentID, filterStudentID, filterCourseID, filterGrade);

        model.addAttribute("enrollments", enrollments);

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "enrollmentID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterEnrollmentID", filterEnrollmentID != null ? filterEnrollmentID : "");
        model.addAttribute("filterStudentID", filterStudentID != null ? filterStudentID : "");
        model.addAttribute("filterCourseID", filterCourseID != null ? filterCourseID : "");
        model.addAttribute("filterGrade", filterGrade != null ? filterGrade : "");

        return "enrollment/index";
    }

    // ================= DETAILS =================
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

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());

        // OBȚINE STUDENȚI ȘI CURSURI
        Map<String, com.example.University.Management.System.model.Student> students = studentService.findAll();
        Map<String, com.example.University.Management.System.model.Course> courses = courseService.findAll();

        System.out.println("DEBUG: Număr studenți: " + students.size());
        System.out.println("DEBUG: Număr cursuri: " + courses.size());

        if (!students.isEmpty()) {
            System.out.println("DEBUG: Primul student: " + students.entrySet().iterator().next());
        }

        model.addAttribute("courses", courses);
        model.addAttribute("students", students);
        return "enrollment/form";
    }

    // ================= EDIT FORM =================
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

    // ================= CREATE =================
    @PostMapping("/create")
    public String createEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        boolean created = enrollmentService.create(enrollment);
        if (!created) {
            model.addAttribute("error", "ID-ul înscrierii '" + enrollment.getEnrollmentID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("enrollment", enrollment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("students", studentService.findAll());
            return "enrollment/form";
        }

        redirectAttributes.addFlashAttribute("message", "Înscriere creată cu succes!");
        return "redirect:/enrollments";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateEnrollment(@Valid @ModelAttribute("enrollment") Enrollment enrollment,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
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

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = enrollmentService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Înscrierea nu există!");
            return "redirect:/enrollments";
        }

        redirectAttributes.addFlashAttribute("message", "Înscriere ștearsă cu succes!");
        return "redirect:/enrollments";
    }
}