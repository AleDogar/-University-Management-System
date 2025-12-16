package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterDepartmentName,
                          @RequestParam(required = false) String filterPhoneNumber,
                          Model model) {

        List<Department> departments = departmentService.findAllWithSortAndFilter(
                sortBy, sortDir, filterDepartmentName, filterPhoneNumber);

        model.addAttribute("departments", departments);

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "departmentID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterDepartmentName", filterDepartmentName != null ? filterDepartmentName : "");
        model.addAttribute("filterPhoneNumber", filterPhoneNumber != null ? filterPhoneNumber : "");

        return "department/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Department department = departmentService.findById(id);
        if (department != null) {
            model.addAttribute("department", department);
            return "department/details";
        }
        redirectAttributes.addFlashAttribute("error", "Departamentul nu există!");
        return "redirect:/departments";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Department department = departmentService.findById(id);
        if (department != null) {
            model.addAttribute("department", department);
            return "department/form";
        }
        redirectAttributes.addFlashAttribute("error", "Departamentul nu există!");
        return "redirect:/departments";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "department/form";
        }

        boolean created = departmentService.create(department);
        if (!created) {
            model.addAttribute("error", "ID-ul departamentului '" + department.getDepartmentID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("department", department);
            return "department/form";
        }

        redirectAttributes.addFlashAttribute("message", "Departament creat cu succes!");
        return "redirect:/departments";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "department/form";
        }

        boolean updated = departmentService.update(department.getDepartmentID(), department);
        if (!updated) {
            model.addAttribute("error", "Departamentul nu există pentru actualizare!");
            return "department/form";
        }

        redirectAttributes.addFlashAttribute("message", "Departament actualizat cu succes!");
        return "redirect:/departments";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = departmentService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Nu se poate șterge departamentul (poate are cursuri/profesori sau nu există)!");
            return "redirect:/departments";
        }

        redirectAttributes.addFlashAttribute("message", "Departament șters cu succes!");
        return "redirect:/departments";
    }
}