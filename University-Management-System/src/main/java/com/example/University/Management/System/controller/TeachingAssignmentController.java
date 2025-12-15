package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.service.TeachingAssignmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Listare asignări
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("assignments", assignmentService.findAll());
        return "teaching-assignment/index";
    }

    // Detalii asignare
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

    // Formular adăugare asignare
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assignment", new TeachingAssignment());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        return "teaching-assignment/form";
    }

    // Formular editare asignare
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

    // Creare asignare
    @PostMapping("/create")
    public String createAssignment(@Valid @ModelAttribute("assignment") TeachingAssignment assignment,
                                   @RequestParam(value = "courseID", required = false) String courseID,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        // Verificăm mai întâi erorile de validare
        if (bindingResult.hasErrors()) {
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }

        // Business validation: Verificăm dacă courseID este valid
        if (courseID == null || courseID.trim().isEmpty()) {
            model.addAttribute("error", "Trebuie să selectați un curs!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }

        // Setăm cursul din courseID
        Course course = courseService.findById(courseID);
        if (course == null) {
            model.addAttribute("error", "Cursul cu ID-ul '" + courseID + "' nu există!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            model.addAttribute("assignment", assignment);
            return "teaching-assignment/form";
        }
        assignment.setCourse(course);

        // Verificăm dacă ID-ul există deja (business validation)
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

    // Actualizare asignare
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

        // Business validation: Verificăm dacă courseID este valid
        if (courseID == null || courseID.trim().isEmpty()) {
            model.addAttribute("error", "Trebuie să selectați un curs!");
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }

        // Setăm cursul din courseID
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

    // Ștergere asignare
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