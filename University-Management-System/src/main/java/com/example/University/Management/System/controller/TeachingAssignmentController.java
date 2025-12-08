package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.service.TeachingAssignmentService;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/teaching-assignments")
public class TeachingAssignmentController {

    private final TeachingAssignmentService teachingAssignmentService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public TeachingAssignmentController(TeachingAssignmentService teachingAssignmentService,
                                        CourseService courseService,
                                        TeacherService teacherService) {
        this.teachingAssignmentService = teachingAssignmentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
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

            Map<String, Course> courses = courseService.findAll();
            Map<String, Teacher> teachers = teacherService.findAll();

            Course course = courses.get(assignment.getCourseId());
            Teacher teacher = teachers.get(assignment.getStaffId());

            if (course != null) model.addAttribute("courseName", course.getTitle());
            if (teacher != null) model.addAttribute("teacherName", teacher.getStaffName());

            return "teaching-assignment/details";
        }
        return "redirect:/teaching-assignments";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assignment", new TeachingAssignment());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        return "teaching-assignment/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        TeachingAssignment assignment = teachingAssignmentService.findById(id);
        if (assignment != null) {
            model.addAttribute("assignment", assignment);
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("teachers", teacherService.findAll());
            return "teaching-assignment/form";
        }
        return "redirect:/teaching-assignments";
    }

    @PostMapping("/create")
    public String createAssignment(@ModelAttribute TeachingAssignment assignment,
                                   RedirectAttributes redirectAttributes) {
        teachingAssignmentService.create(assignment);
        redirectAttributes.addFlashAttribute("message", "Asignare creată cu succes!");
        return "redirect:/teaching-assignments";
    }

    @PostMapping("/update")
    public String updateAssignment(@ModelAttribute TeachingAssignment assignment,
                                   RedirectAttributes redirectAttributes) {
        teachingAssignmentService.update(assignment.getId(), assignment);
        redirectAttributes.addFlashAttribute("message", "Asignare actualizată cu succes!");
        return "redirect:/teaching-assignments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        teachingAssignmentService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Asignare ștearsă cu succes!");
        return "redirect:/teaching-assignments";
    }
}
