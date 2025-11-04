package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.model.University;
import com.example.University.Management.System.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;
    public TeacherController(TeacherService service) {
        this.service = service;
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("teachers", service.getAllTeachers());
        return "teacher/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("teacher", new Teacher(null, "", ""));
        return "teacher/form";
    }

    @PostMapping
    public String add(@ModelAttribute Teacher t) {
        service.addTeacher(t);
        return "redirect:/teachers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeTeacher(id);
        return "redirect:/teachers";
    }

}
