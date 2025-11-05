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

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "student/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student(null, "", "", ""));
        return "student/form";
    }

    @PostMapping
    public String add(@ModelAttribute Student s) {
        service.addStudent(s);
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeStudent(id);
        return "redirect:/students";
    }
}
