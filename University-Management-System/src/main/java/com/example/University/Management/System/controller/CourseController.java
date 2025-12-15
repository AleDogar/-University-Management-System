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

    // Listare cursuri
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/index";
    }

    // Detalii curs
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

    // Formular adăugare curs
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("rooms", roomService.findAll());
        return "course/form";
    }

    // Formular editare curs
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Course course = courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }
        redirectAttributes.addFlashAttribute("error", "Cursul nu există!");
        return "redirect:/courses";
    }

    // Creare curs
    @PostMapping("/create")
    public String createCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        // 1. Verificăm validările de câmpuri (@NotBlank, @Size, etc.)
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        // 2. Business validation: Verificăm dacă ID-ul există deja
        if (courseService.findById(course.getCourseID()) != null) {
            bindingResult.rejectValue("courseID", "error.course",
                    "ID-ul cursului '" + course.getCourseID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        // 3. Business validation: Verificăm dacă departamentul există
        if (!courseService.departmentExists(course.getDepartmentID())) {
            bindingResult.rejectValue("departmentID", "error.course",
                    "Departamentul cu ID-ul '" + course.getDepartmentID() + "' nu există!");
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        // 4. Business validation: Verificăm dacă sala există
        if (!courseService.roomExists(course.getRoomID())) {
            bindingResult.rejectValue("roomID", "error.course",
                    "Sala cu ID-ul '" + course.getRoomID() + "' nu există!");
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        // 5. Dacă toate validările au trecut, creăm cursul
        boolean created = courseService.create(course);
        if (!created) {
            model.addAttribute("error", "Eroare la crearea cursului! Verificați datele introduse.");
            model.addAttribute("course", course);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        redirectAttributes.addFlashAttribute("message", "Curs creat cu succes!");
        return "redirect:/courses";
    }

    // Actualizare curs
    @PostMapping("/update")
    public String updateCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        // 1. Verificăm validările de câmpuri
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        // 2. Business validation: Verificăm dacă departamentul există
        if (!courseService.departmentExists(course.getDepartmentID())) {
            bindingResult.rejectValue("departmentID", "error.course",
                    "Departamentul cu ID-ul '" + course.getDepartmentID() + "' nu există!");
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        // 3. Business validation: Verificăm dacă sala există
        if (!courseService.roomExists(course.getRoomID())) {
            bindingResult.rejectValue("roomID", "error.course",
                    "Sala cu ID-ul '" + course.getRoomID() + "' nu există!");
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        // 4. Actualizăm cursul
        boolean updated = courseService.update(course.getCourseID(), course);
        if (!updated) {
            model.addAttribute("error", "Cursul nu există pentru actualizare!");
            model.addAttribute("course", course);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "course/form";
        }

        redirectAttributes.addFlashAttribute("message", "Curs actualizat cu succes!");
        return "redirect:/courses";
    }

    // Ștergere curs
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = courseService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error",
                    "Nu se poate șterge cursul! Cursul nu există sau are atribuiri de predare.");
            return "redirect:/courses";
        }

        redirectAttributes.addFlashAttribute("message", "Curs șters cu succes!");
        return "redirect:/courses";
    }
}