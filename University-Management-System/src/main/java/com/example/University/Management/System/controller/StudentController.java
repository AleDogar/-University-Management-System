package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("students", service.findAll());
        return "student/index";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Student student = service.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/details";
        }
        return "redirect:/students";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/form";
        }
        return "redirect:/students";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        service.create(student);
        redirectAttributes.addFlashAttribute("message", "Student creat cu succes!");
        return "redirect:/students";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        service.update(student.getId(), student);
        redirectAttributes.addFlashAttribute("message", "Student actualizat cu succes!");
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Student șters cu succes!");
        return "redirect:/students";
    }
}
