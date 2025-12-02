package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // List all students
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("students", service.findAll());
        return "student/index";
    }

    // Show form for adding a new student
    @GetMapping("/new")
    public String showAddForm(Model model) {
        // Folosim constructorul fără parametri și setăm câmpurile goale
        Student student = new Student();
        student.setId(null);
        student.setName("");
        student.setEmail("");
        model.addAttribute("student", student);
        return "student/form";
    }

    // Show form for editing an existing student
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/form";
        }
        return "redirect:/students";
    }

    // Save a new or edited student
    @PostMapping("/create")
    public String create(@ModelAttribute Student student) {
        service.create(student);
        return "redirect:/students";
    }

    // Delete a student
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/students";
    }

    // View student details
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Student student = service.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/details";
        }
        return "redirect:/students";
    }
}
