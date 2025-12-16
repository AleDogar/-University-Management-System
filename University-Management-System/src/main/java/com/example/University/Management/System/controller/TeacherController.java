package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.service.TeacherService;
import com.example.University.Management.System.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final DepartmentService departmentService;

    public TeacherController(TeacherService teacherService, DepartmentService departmentService) {
        this.teacherService = teacherService;
        this.departmentService = departmentService;
    }

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterStaffName,
                          @RequestParam(required = false) String filterTitle,
                          @RequestParam(required = false) String filterDepartmentID,
                          @RequestParam(required = false) String filterEmail,
                          Model model) {

        List<Teacher> teachers = teacherService.findAllWithSortAndFilter(
                sortBy, sortDir, filterStaffName, filterTitle, filterDepartmentID, filterEmail);

        model.addAttribute("teachers", teachers);
        model.addAttribute("departments", departmentService.findAll());

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "staffID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterStaffName", filterStaffName != null ? filterStaffName : "");
        model.addAttribute("filterTitle", filterTitle != null ? filterTitle : "");
        model.addAttribute("filterDepartmentID", filterDepartmentID != null ? filterDepartmentID : "");
        model.addAttribute("filterEmail", filterEmail != null ? filterEmail : "");

        return "teacher/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherService.findById(id);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
            return "teacher/details";
        }
        redirectAttributes.addFlashAttribute("error", "Profesorul nu există!");
        return "redirect:/teachers";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("departments", departmentService.findAll());
        return "teacher/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Teacher teacher = teacherService.findById(id);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }
        redirectAttributes.addFlashAttribute("error", "Profesorul nu există!");
        return "redirect:/teachers";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createTeacher(@Valid @ModelAttribute("teacher") Teacher teacher,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }

        if (teacherService.findById(teacher.getStaffID()) != null) {
            model.addAttribute("error", "ID-ul profesorului '" + teacher.getStaffID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("teacher", teacher);
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }

        if (!teacherService.departmentExists(teacher.getDepartmentID())) {
            model.addAttribute("error", "Departamentul cu ID-ul '" + teacher.getDepartmentID() + "' nu există!");
            model.addAttribute("teacher", teacher);
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }

        boolean created = teacherService.create(teacher);
        if (!created) {
            model.addAttribute("error", "Eroare la crearea profesorului! Verificați datele introduse.");
            model.addAttribute("teacher", teacher);
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }

        redirectAttributes.addFlashAttribute("message", "Profesor creat cu succes!");
        return "redirect:/teachers";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateTeacher(@Valid @ModelAttribute("teacher") Teacher teacher,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }

        if (!teacherService.departmentExists(teacher.getDepartmentID())) {
            model.addAttribute("error", "Departamentul cu ID-ul '" + teacher.getDepartmentID() + "' nu există!");
            model.addAttribute("teacher", teacher);
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }

        boolean updated = teacherService.update(teacher.getStaffID(), teacher);
        if (!updated) {
            model.addAttribute("error", "Profesorul nu există pentru actualizare!");
            model.addAttribute("teacher", teacher);
            model.addAttribute("departments", departmentService.findAll());
            return "teacher/form";
        }

        redirectAttributes.addFlashAttribute("message", "Profesor actualizat cu succes!");
        return "redirect:/teachers";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = teacherService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error",
                    "Nu se poate șterge profesorul! Profesorul nu există sau are asignări de predare.");
            return "redirect:/teachers";
        }

        redirectAttributes.addFlashAttribute("message", "Profesor șters cu succes!");
        return "redirect:/teachers";
    }
}