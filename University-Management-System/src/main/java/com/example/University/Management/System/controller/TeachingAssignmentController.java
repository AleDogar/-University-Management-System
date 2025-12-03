package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.service.TeachingAssignmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.validation.TeachingAssignmentValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/teaching-assignments")
public class TeachingAssignmentController {

    private final TeachingAssignmentService teachingAssignmentService;
    private final CourseService courseService;
    private final TeachingAssignmentValidator validator;

    public TeachingAssignmentController(TeachingAssignmentService teachingAssignmentService,
                                        CourseService courseService) {
        this.teachingAssignmentService = teachingAssignmentService;
        this.courseService = courseService;
        this.validator = new TeachingAssignmentValidator();
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("assignments", teachingAssignmentService.findAll());
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
    public String showAddForm(Model model) {
        model.addAttribute("assignment", new TeachingAssignment());
        model.addAttribute("courses", courseService.findAll());
        return "teaching-assignment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        TeachingAssignment assignment = teachingAssignmentService.findById(id);
        if (assignment != null) {
            model.addAttribute("assignment", assignment);
            model.addAttribute("courses", courseService.findAll());
            return "teaching-assignment/form";
        }
        return "redirect:/teaching-assignments";
    }

    @PostMapping("/create")
    public String createAssignment(@ModelAttribute TeachingAssignment assignment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {
            validator.validateTeachingAssignment(assignment);

            TeachingAssignment existing = teachingAssignmentService.findById(assignment.getId());
            if (existing != null) {
                throw new RuntimeException("Există deja o atribuire cu acest ID.");
            }

            if (courseService.findById(assignment.getCourseId()) == null) {
                throw new RuntimeException("Cursul cu ID " + assignment.getCourseId() + " nu există.");
            }

            teachingAssignmentService.create(assignment);
            redirectAttributes.addFlashAttribute("message", "Atribuire creată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("assignment", assignment);
            model.addAttribute("courses", courseService.findAll());
            return "teaching-assignment/form";
        }

        return "redirect:/teaching-assignments";
    }

    @PostMapping("/update")
    public String updateAssignment(@ModelAttribute TeachingAssignment assignment,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        try {
            validator.validateTeachingAssignment(assignment);

            TeachingAssignment existing = teachingAssignmentService.findById(assignment.getId());
            if (existing == null) {
                throw new RuntimeException("Atribuirea nu există.");
            }

            if (courseService.findById(assignment.getCourseId()) == null) {
                throw new RuntimeException("Cursul cu ID " + assignment.getCourseId() + " nu există.");
            }

            teachingAssignmentService.update(assignment.getId(), assignment);
            redirectAttributes.addFlashAttribute("message", "Atribuire actualizată cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("assignment", assignment);
            model.addAttribute("courses", courseService.findAll());
            return "teaching-assignment/form";
        }

        return "redirect:/teaching-assignments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            teachingAssignmentService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Atribuire ștearsă cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/teaching-assignments";
    }
}