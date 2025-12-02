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

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("courses", service.findAll());
        return "course/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "course/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Course course = service.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "course/form";
        }
        return "redirect:/courses";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Course course) {
        service.create(course);
        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable String id) {
        service.delete(id);
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Course course = service.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "course/details";
        }
        return "redirect:/courses";
    }
}
