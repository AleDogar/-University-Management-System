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
    public TeachingAssignmentController(TeachingAssignmentService service) { this.service = service; }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("teachingassignments", service.getAllTeachingAssignments());
        model.addAttribute("types", ClassType.values());
        return "teachingassignment/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("teachingAssignment", new TeachingAssignment(null, ClassType.Course, "", ""));
        model.addAttribute("types", ClassType.values());
        return "teachingassignment/form";
    }

    @PostMapping
    public String add(@ModelAttribute TeachingAssignment t) {
        service.addTeachingAssignment(t);
        return "redirect:/teachingassignments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeTeachingAssignment(id);
        return "redirect:/teachingassignments";
    }
}
