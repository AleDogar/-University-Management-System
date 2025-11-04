package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;
    public CourseController(CourseService service) { this.service = service; }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("courses", service.getAllCourses());
        return "course/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course(null, "", 0, "", "", null, null));
        return "course/form";
    }

    @PostMapping
    public String addCourse(@ModelAttribute Course course) {
        service.addCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable String id) {
        service.removeCourse(id);
        return "redirect:/courses";
    }
}