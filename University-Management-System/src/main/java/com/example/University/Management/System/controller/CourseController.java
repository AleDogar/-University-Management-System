package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.DepartmentService;
import com.example.University.Management.System.service.RoomService;
import com.example.University.Management.System.validation.CourseValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final DepartmentService departmentService;
    private final RoomService roomService;

    private final CourseValidator courseValidator;

    public CourseController(CourseService courseService,
                            DepartmentService departmentService,
                            RoomService roomService) {
        this.courseService = courseService;
        this.departmentService = departmentService;
        this.roomService = roomService;
        this.courseValidator = new CourseValidator(departmentService, roomService);
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("rooms", roomService.findAll());
        return "course/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Course course = courseService.findById(id);
        if (course == null) return "redirect:/courses";

        model.addAttribute("course", course);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("rooms", roomService.findAll());
        return "course/form";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute Course course,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        try {
            courseValidator.validateCourse(course);

            if (courseService.findById(course.getCourseID()) != null) {
                throw new RuntimeException("Există deja un curs cu acest ID.");
            }

            courseService.create(course);
            redirectAttributes.addFlashAttribute("message", "Curs creat cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("course", course);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        return "redirect:/courses";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute Course course,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        try {
            courseValidator.validateCourse(course);

            if (courseService.findById(course.getCourseID()) == null) {
                throw new RuntimeException("Cursul nu există.");
            }

            courseService.update(course.getCourseID(), course);
            redirectAttributes.addFlashAttribute("message", "Curs actualizat cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("course", course);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            courseService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Curs șters cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/courses";
    }
}
