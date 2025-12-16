package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Listare studenți
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/index";
    }

    // Detalii student
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

    // Formular adăugare student
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }

    // Formular editare student
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

    // Creare student
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        // Verificăm mai întâi erorile de validare
        if (bindingResult.hasErrors()) {
            return "student/form";
        }

        // Verificăm dacă ID-ul există deja (business validation)
        boolean created = studentService.create(student);
        if (!created) {
            model.addAttribute("error", "ID-ul studentului '" + student.getStudentID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("student", student);
            return "student/form";
        }

        redirectAttributes.addFlashAttribute("message", "Student creat cu succes!");
        return "redirect:/students";
    }

    // Actualizare student
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

    // Ștergere student
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