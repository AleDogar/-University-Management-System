package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.DepartmentService;
import com.example.University.Management.System.service.RoomService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;
    private final DepartmentService departmentService;
    private final RoomService roomService;

    public CourseController(CourseService courseService,
                            DepartmentService departmentService,
                            RoomService roomService) {
        this.courseService = courseService;
        this.departmentService = departmentService;
        this.roomService = roomService;
    }

    @GetMapping
    public String listAll(Model model) {
        log.debug("GET /courses - listAll called");
        model.addAttribute("courses", courseService.findAll());
        return "course/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        log.debug("GET /courses/new - showAddForm called");
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("rooms", roomService.findAll());
        return "course/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        log.debug("GET /courses/{}/edit - showEditForm called", id);
        Course course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }
        log.warn("Course id {} not found for edit", id);
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        log.debug("GET /courses/{} - viewDetails called", id);
        Course course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "course/details";
        }
        log.warn("Course id {} not found for details", id);
        return "redirect:/courses";
    }

    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        log.debug("POST /courses/create - createCourse called with id={}", course.getCourseID());
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            log.debug("Validation errors on create: {}", bindingResult.getAllErrors());
            return "course/form";
        }

        try {
            boolean created = courseService.create(course);
            if (!created) {
                model.addAttribute("departments", departmentService.findAll());
                model.addAttribute("rooms", roomService.findAll());
                model.addAttribute("error", "Un curs cu acest ID există deja.");
                log.info("Create failed: duplicate id {}", course.getCourseID());
                return "course/form";
            }
        } catch (IllegalArgumentException ex) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("error", ex.getMessage());
            log.info("Create failed business validation: {}", ex.getMessage());
            return "course/form";
        }

        redirectAttributes.addFlashAttribute("message", "Curs creat cu succes!");
        return "redirect:/courses";
    }

    @PostMapping("/update")
    public String updateCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        log.debug("POST /courses/update - updateCourse called with id={}", course.getCourseID());
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            log.debug("Validation errors on update: {}", bindingResult.getAllErrors());
            return "course/form";
        }

        try {
            boolean ok = courseService.update(course.getCourseID(), course);
            if (!ok) {
                model.addAttribute("departments", departmentService.findAll());
                model.addAttribute("rooms", roomService.findAll());
                model.addAttribute("error", "Nu se poate actualiza: cursul nu există.");
                log.info("Update failed: course id {} does not exist", course.getCourseID());
                return "course/form";
            }
        } catch (IllegalArgumentException ex) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("error", ex.getMessage());
            log.info("Update failed business validation: {}", ex.getMessage());
            return "course/form";
        }

        redirectAttributes.addFlashAttribute("message", "Curs actualizat cu succes!");
        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        log.debug("POST /courses/{}/delete - delete called", id);
        boolean deleted = courseService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Nu se poate șterge cursul (poate are atribuiri sau nu există)!");
            log.info("Delete failed for id {}", id);
            return "redirect:/courses";
        }

        redirectAttributes.addFlashAttribute("message", "Curs șters cu succes!");
        log.info("Course {} deleted", id);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/delete")
    public String deleteGet(@PathVariable String id, RedirectAttributes redirectAttributes) {
        // Delegate to the POST delete for convenience/testing
        return delete(id, redirectAttributes);
    }
}
