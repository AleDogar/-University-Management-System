package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.service.TeacherService;
import com.example.University.Management.System.validation.TeacherValidator;
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

    // CREATE (profesor nou)
    @PostMapping("/create")
    public String createTeacher(@ModelAttribute Teacher teacher,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        try {
            // 1. Validare câmpuri cu TeacherValidator
            TeacherValidator.validateTeacher(teacher);

            // 2. Verifică dacă ID-ul există deja în baza de date
            Teacher existingTeacher = service.findById(teacher.getStaffID());
            if (existingTeacher != null) {
                throw new RuntimeException("Există deja un profesor cu acest ID.");
            }

            // 3. Creează profesor nou
            service.create(teacher);
            redirectAttributes.addFlashAttribute("message", "Profesor creat cu succes!");

        } catch (RuntimeException e) {
            // Prinde eroarea (fie de la TeacherValidator, fie de la verificarea ID duplicat)
            model.addAttribute("error", e.getMessage());
            model.addAttribute("teacher", teacher); // Păstrează datele introduse
            return "teacher/form";
        }

        return "redirect:/teachers";
    }

    // UPDATE (editare)
    @PostMapping("/update")
    public String updateTeacher(@ModelAttribute Teacher teacher,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        try {
            // 1. Validare câmpuri
            TeacherValidator.validateTeacher(teacher);

            // 2. Verifică că profesorul există (pentru update)
            Teacher existingTeacher = service.findById(teacher.getStaffID());
            if (existingTeacher == null) {
                throw new RuntimeException("Profesorul nu există.");
            }

            // 3. Face update
            service.update(teacher.getStaffID(), teacher);
            redirectAttributes.addFlashAttribute("message", "Profesor actualizat cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("teacher", teacher);
            return "teacher/form";
        }

        return "redirect:/teachers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "Profesor șters cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teachers";
    }
}