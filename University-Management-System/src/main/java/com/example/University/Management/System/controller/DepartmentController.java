package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Listare departamente
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "department/index";
    }

    // Detalii departament
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

    // Formular adăugare departament
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/form";
    }

    // Formular editare departament
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

    // Creare departament
    @PostMapping("/create")
    public String createDepartment(@Valid @ModelAttribute("department") Department department,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        // Verificăm mai întâi erorile de validare
        if (bindingResult.hasErrors()) {
            return "department/form";
        }

        // Verificăm dacă ID-ul există deja (business validation)
        boolean created = departmentService.create(department);
        if (!created) {
            // Adăugăm eroarea în model pentru a fi afișată în formular
            model.addAttribute("error", "ID-ul departamentului '" + department.getDepartmentID() + "' există deja! Alegeți un alt ID.");
            // Returnăm la formular cu datele introduse
            model.addAttribute("department", department);
            return "department/form";
        }

        redirectAttributes.addFlashAttribute("message", "Departament creat cu succes!");
        return "redirect:/departments";
    }

    // Actualizare departament
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

    // Ștergere departament
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