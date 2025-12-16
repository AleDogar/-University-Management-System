package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.model.ClassType;
import com.example.University.Management.System.service.TeachingAssignmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/teaching-assignments")
public class TeachingAssignmentController {

    private final TeachingAssignmentService assignmentService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public TeachingAssignmentController(TeachingAssignmentService assignmentService,
                                        CourseService courseService,
                                        TeacherService teacherService) {
        this.assignmentService = assignmentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterClassType,
                          @RequestParam(required = false) String filterStaffID,
                          @RequestParam(required = false) String filterCourseID,
                          Model model) {

        List<TeachingAssignment> assignments = assignmentService.findAllWithSortAndFilter(
                sortBy, sortDir, filterClassType, filterStaffID, filterCourseID);

        model.addAttribute("assignments", assignments);
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacherService.findAll());

        // Adăugăm toate tipurile de clasă pentru dropdown
        model.addAttribute("allClassTypes", ClassType.values());

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "assignmentID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterClassType", filterClassType != null ? filterClassType : "");
        model.addAttribute("filterStaffID", filterStaffID != null ? filterStaffID : "");
        model.addAttribute("filterCourseID", filterCourseID != null ? filterCourseID : "");

        return "teaching-assignment/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        TeachingAssignment assignment = assignmentService.findById(id);
        if (assignment != null) {
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/details";
        }
        redirectAttributes.addFlashAttribute("error", "Asignarea nu există!");
        return "redirect:/teaching-assignments";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assignment", new TeachingAssignment());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        return "teaching-assignment/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        TeachingAssignment assignment = assignmentService.findById(id);
        if (assignment != null) {
            model.addAttribute("assignment", assignment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }
        redirectAttributes.addFlashAttribute("error", "Asignarea nu există!");
        return "redirect:/teaching-assignments";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createAssignment(@Valid @ModelAttribute("assignment") TeachingAssignment assignment,
                                   @RequestParam(value = "courseID", required = false) String courseID,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }

        if (courseID == null || courseID.trim().isEmpty()) {
            model.addAttribute("error", "Trebuie să selectați un curs!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }

        Course course = courseService.findById(courseID);
        if (course == null) {
            model.addAttribute("error", "Cursul cu ID-ul '" + courseID + "' nu există!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }
        assignment.setCourse(course);

        boolean created = assignmentService.create(assignment);
        if (!created) {
            model.addAttribute("error", "ID-ul asignării '" + assignment.getAssignmentID() + "' există deja sau datele sunt invalide! Alegeți un alt ID.");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }

        redirectAttributes.addFlashAttribute("message", "Asignare creată cu succes!");
        return "redirect:/teaching-assignments";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateAssignment(@Valid @ModelAttribute("assignment") TeachingAssignment assignment,
                                   @RequestParam(value = "courseID", required = false) String courseID,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }

        if (courseID == null || courseID.trim().isEmpty()) {
            model.addAttribute("error", "Trebuie să selectați un curs!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }

        Course course = courseService.findById(courseID);
        if (course == null) {
            model.addAttribute("error", "Cursul cu ID-ul '" + courseID + "' nu există!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }
        assignment.setCourse(course);

        boolean updated = assignmentService.update(assignment.getAssignmentID(), assignment);
        if (!updated) {
            model.addAttribute("error", "Asignarea nu există pentru actualizare!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }

        redirectAttributes.addFlashAttribute("message", "Asignare actualizată cu succes!");
        return "redirect:/teaching-assignments";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = assignmentService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Asignarea nu există!");
            return "redirect:/teaching-assignments";
        }

        redirectAttributes.addFlashAttribute("message", "Asignare ștearsă cu succes!");
        return "redirect:/teaching-assignments";
    }
}