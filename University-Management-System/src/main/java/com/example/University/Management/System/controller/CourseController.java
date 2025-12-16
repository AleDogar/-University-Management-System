package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.service.CourseService;
import com.example.University.Management.System.service.DepartmentService;
import com.example.University.Management.System.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

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

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterTitle,
                          @RequestParam(required = false) Integer minCredits,
                          @RequestParam(required = false) Integer maxCredits,
                          @RequestParam(required = false) String filterDepartmentID,
                          @RequestParam(required = false) String filterRoomID,
                          Model model) {

        List<Course> courses = courseService.findAllWithSortAndFilter(
                sortBy, sortDir, filterTitle, minCredits, maxCredits,
                filterDepartmentID, filterRoomID);

        model.addAttribute("courses", courses);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("rooms", roomService.findAll());

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "courseID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterTitle", filterTitle != null ? filterTitle : "");
        model.addAttribute("minCredits", minCredits);
        model.addAttribute("maxCredits", maxCredits);
        model.addAttribute("filterDepartmentID", filterDepartmentID != null ? filterDepartmentID : "");
        model.addAttribute("filterRoomID", filterRoomID != null ? filterRoomID : "");

        return "course/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Course course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "course/details";
        }
        redirectAttributes.addFlashAttribute("error", "Cursul nu există!");
        return "redirect:/courses";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("rooms", roomService.findAll());
        model.addAttribute("isEdit", false);
        return "course/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        Course course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("isEdit", true);
            return "course/form";
        }

        redirectAttributes.addFlashAttribute("error", "Cursul nu există!");
        return "redirect:/courses";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("isEdit", false);
            return "course/form";
        }

        boolean created = courseService.create(course);
        if (!created) {
            model.addAttribute("error",
                    "ID-ul cursului există deja sau departamentul/sala nu există!");
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("isEdit", false);
            return "course/form";
        }

        redirectAttributes.addFlashAttribute("message", "Curs creat cu succes!");
        return "redirect:/courses";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("isEdit", true);
            return "course/form";
        }

        boolean updated = courseService.update(course.getCourseID(), course);
        if (!updated) {
            model.addAttribute("error",
                    "Cursul nu există sau departamentul/sala nu există!");
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("isEdit", true);
            return "course/form";
        }

        redirectAttributes.addFlashAttribute("message", "Curs actualizat cu succes!");
        return "redirect:/courses";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {

        boolean deleted = courseService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Nu se poate șterge cursul (poate are atribuiri de predare sau nu există)!"
            );
            return "redirect:/courses";
        }

        redirectAttributes.addFlashAttribute("message", "Curs șters cu succes!");
        return "redirect:/courses";
    }
}