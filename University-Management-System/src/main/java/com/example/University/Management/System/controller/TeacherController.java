package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    @Autowired
    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("teachers", service.findAll());
        return "teacher/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Teacher teacher = service.findById(id);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
            return "teacher/form";
        }
        return "redirect:/teachers";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Teacher teacher) {
        service.create(teacher);
        return "redirect:/teachers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Teacher teacher = service.findById(id);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
            return "teacher/details";
        }
        return "redirect:/teachers";
    }
}
