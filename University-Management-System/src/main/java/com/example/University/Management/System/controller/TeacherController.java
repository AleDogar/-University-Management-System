package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("teachers", service.findAll());
        return "teacher/index";
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
    public String createTeacher(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
        service.create(teacher);
        redirectAttributes.addFlashAttribute("message", "Profesor creat cu succes!");
        return "redirect:/teachers";
    }

    @PostMapping("/update")
    public String updateTeacher(@ModelAttribute Teacher teacher, RedirectAttributes redirectAttributes) {
        service.update(teacher.getStaffID(), teacher);
        redirectAttributes.addFlashAttribute("message", "Profesor actualizat cu succes!");
        return "redirect:/teachers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Profesor șters cu succes!");
        return "redirect:/teachers";
    }
}
