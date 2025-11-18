package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.ClassType;
import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.service.TeachingAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachingassignments")
public class TeachingAssignmentController {

    private final TeachingAssignmentService service;

    public TeachingAssignmentController(TeachingAssignmentService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("teachingAssignments", service.getAllTeachingAssignments());
        model.addAttribute("types", ClassType.values());
        return "teachingassignment/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("teachingAssignment", new TeachingAssignment());
        model.addAttribute("types", ClassType.values());
        return "teachingassignment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        TeachingAssignment ta = service.getTeachingAssignmentById(id);
        if (ta != null) {
            model.addAttribute("teachingAssignment", ta);
            model.addAttribute("types", ClassType.values());
            return "teachingassignment/form";
        }
        return "redirect:/teachingassignments";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TeachingAssignment ta) {
        service.saveTeachingAssignment(ta);
        return "redirect:/teachingassignments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteTeachingAssignment(id);
        return "redirect:/teachingassignments";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        TeachingAssignment ta = service.getTeachingAssignmentById(id);
        if (ta != null) {
            model.addAttribute("teachingAssignment", ta);
            return "teachingassignment/details";
        }
        return "redirect:/teachingassignments";
    }
}
