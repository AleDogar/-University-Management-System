package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterStudentName,
                          @RequestParam(required = false) String filterEmail,
                          Model model) {

        List<Student> students = studentService.findAllWithSortAndFilter(
                sortBy, sortDir, filterStudentName, filterEmail);

        model.addAttribute("students", students);

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "studentID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterStudentName", filterStudentName != null ? filterStudentName : "");
        model.addAttribute("filterEmail", filterEmail != null ? filterEmail : "");

        return "student/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Student student = studentService.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/details";
        }
        redirectAttributes.addFlashAttribute("error", "Studentul nu există!");
        return "redirect:/students";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Student student = studentService.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/form";
        }
        redirectAttributes.addFlashAttribute("error", "Studentul nu există!");
        return "redirect:/students";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "student/form";
        }

        boolean created = studentService.create(student);
        if (!created) {
            model.addAttribute("error", "ID-ul studentului '" + student.getStudentID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("student", student);
            return "student/form";
        }

        redirectAttributes.addFlashAttribute("message", "Student creat cu succes!");
        return "redirect:/students";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "student/form";
        }

        boolean updated = studentService.update(student.getStudentID(), student);
        if (!updated) {
            model.addAttribute("error", "Studentul nu există pentru actualizare!");
            return "student/form";
        }

        redirectAttributes.addFlashAttribute("message", "Student actualizat cu succes!");
        return "redirect:/students";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = studentService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Studentul nu există!");
            return "redirect:/students";
        }

        redirectAttributes.addFlashAttribute("message", "Student șters cu succes!");
        return "redirect:/students";
    }
}