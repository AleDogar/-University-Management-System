package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.service.TeachingAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/teaching-assignments")
public class TeachingAssignmentController {

    private final TeachingAssignmentService teachingAssignmentService;

    public TeachingAssignmentController(TeachingAssignmentService teachingAssignmentService) {
        this.teachingAssignmentService = teachingAssignmentService;
    }

    @GetMapping
    public String listAll(Model model) {
        Map<String, TeachingAssignment> assignments = teachingAssignmentService.findAll();
        model.addAttribute("assignments", assignments);
        return "teaching-assignment/index";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        TeachingAssignment assignment = teachingAssignmentService.findById(id);
        if (assignment != null) {
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/details";
        }
        return "redirect:/teaching-assignments";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new TeachingAssignment());
        return "teaching-assignment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        TeachingAssignment assignment = teachingAssignmentService.findById(id);
        if (assignment != null) {
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }
        return "redirect:/teaching-assignments";
    }

    @PostMapping("/create")
    public String createAssignment(@ModelAttribute TeachingAssignment assignment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        try {
            // VALIDARE SIMPLĂ
            if (assignment.getId() == null || assignment.getId().trim().isEmpty()) {
                throw new RuntimeException("ID-ul asignării este obligatoriu!");
            }

            if (assignment.getCourseId() == null || assignment.getCourseId().trim().isEmpty()) {
                throw new RuntimeException("ID-ul cursului este obligatoriu!");
            }

            if (assignment.getStaffId() == null || assignment.getStaffId().trim().isEmpty()) {
                throw new RuntimeException("ID-ul staff-ului este obligatoriu!");
            }

            if (assignment.getClassType() == null) {
                throw new RuntimeException("Tipul clasei este obligatoriu!");
            }

            // VERIFICĂ DACA EXISTĂ DEJA
            TeachingAssignment existing = teachingAssignmentService.findById(assignment.getId());
            if (existing != null) {
                throw new RuntimeException("Asignarea cu ID-ul " + assignment.getId() + " există deja!");
            }

            // CREARE
            teachingAssignmentService.create(assignment);
            redirectAttributes.addFlashAttribute("message", "Asignare creată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }

        return "redirect:/teaching-assignments";
    }

    @PostMapping("/update")
    public String updateAssignment(@ModelAttribute TeachingAssignment assignment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        try {
            // VALIDARE SIMPLĂ
            if (assignment.getId() == null || assignment.getId().trim().isEmpty()) {
                throw new RuntimeException("ID-ul asignării este obligatoriu!");
            }

            if (assignment.getCourseId() == null || assignment.getCourseId().trim().isEmpty()) {
                throw new RuntimeException("ID-ul cursului este obligatoriu!");
            }

            if (assignment.getStaffId() == null || assignment.getStaffId().trim().isEmpty()) {
                throw new RuntimeException("ID-ul staff-ului este obligatoriu!");
            }

            if (assignment.getClassType() == null) {
                throw new RuntimeException("Tipul clasei este obligatoriu!");
            }

            // VERIFICĂ DACA EXISTĂ
            TeachingAssignment existing = teachingAssignmentService.findById(assignment.getId());
            if (existing == null) {
                throw new RuntimeException("Asignarea cu ID-ul " + assignment.getId() + " nu există!");
            }

            // ACTUALIZARE
            teachingAssignmentService.update(assignment.getId(), assignment);
            redirectAttributes.addFlashAttribute("message", "Asignare actualizată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }

        return "redirect:/teaching-assignments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssignment(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            teachingAssignmentService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Asignare ștearsă cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teaching-assignments";
    }
}