package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.model.ClassRole;
import com.example.University.Management.System.service.AssistantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/assistants")
public class AssistantController {

    private final AssistantService assistantService;

    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterStaffName,
                          @RequestParam(required = false) String filterRole,
                          @RequestParam(required = false) String filterEmail,
                          Model model) {

        List<Assistant> assistants = assistantService.findAllWithSortAndFilter(
                sortBy, sortDir, filterStaffName, filterRole, filterEmail);

        model.addAttribute("assistants", assistants);
        model.addAttribute("allRoles", ClassRole.values());

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "staffID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterStaffName", filterStaffName != null ? filterStaffName : "");
        model.addAttribute("filterRole", filterRole != null ? filterRole : "");
        model.addAttribute("filterEmail", filterEmail != null ? filterEmail : "");

        return "assistant/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Assistant assistant = assistantService.findById(id);
        if (assistant != null) {
            model.addAttribute("assistant", assistant);
            return "assistant/details";
        }
        redirectAttributes.addFlashAttribute("error", "Asistentul nu există!");
        return "redirect:/assistants";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assistant", new Assistant());
        return "assistant/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Assistant assistant = assistantService.findById(id);
        if (assistant != null) {
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }
        redirectAttributes.addFlashAttribute("error", "Asistentul nu există!");
        return "redirect:/assistants";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createAssistant(@Valid @ModelAttribute("assistant") Assistant assistant,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "assistant/form";
        }

        // Business validation: Verificăm dacă ID-ul există deja
        if (assistantService.findById(assistant.getStaffID()) != null) {
            model.addAttribute("error", "ID-ul asistentului '" + assistant.getStaffID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        boolean created = assistantService.create(assistant);
        if (!created) {
            model.addAttribute("error", "Eroare la crearea asistentului! Verificați datele introduse.");
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        redirectAttributes.addFlashAttribute("message", "Asistent creat cu succes!");
        return "redirect:/assistants";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateAssistant(@Valid @ModelAttribute("assistant") Assistant assistant,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "assistant/form";
        }

        boolean updated = assistantService.update(assistant.getStaffID(), assistant);
        if (!updated) {
            model.addAttribute("error", "Asistentul nu există pentru actualizare!");
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        redirectAttributes.addFlashAttribute("message", "Asistent actualizat cu succes!");
        return "redirect:/assistants";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = assistantService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error", "Asistentul nu există!");
            return "redirect:/assistants";
        }

        redirectAttributes.addFlashAttribute("message", "Asistent șters cu succes!");
        return "redirect:/assistants";
    }
}