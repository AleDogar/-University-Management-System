package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.service.AssistantService;
import com.example.University.Management.System.validation.AssistantValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/assistants")
public class AssistantController {

    private final AssistantService service;

    public AssistantController(AssistantService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("assistants", service.findAll());
        return "assistant/index";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Assistant assistant = service.findById(id);
        if (assistant != null) {
            model.addAttribute("assistant", assistant);
            return "assistant/details";
        }
        return "redirect:/assistants";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assistant", new Assistant());
        return "assistant/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Assistant assistant = service.findById(id);
        if (assistant != null) {
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }
        return "redirect:/assistants";
    }

    // CREATE (asistent nou)
    @PostMapping("/create")
    public String createAssistant(@ModelAttribute Assistant assistant,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        try {
            // Validare câmpuri
            AssistantValidator.validateAssistant(assistant);

            // Verifică dacă ID-ul există deja
            Assistant existingAssistant = service.findById(assistant.getStaffID());
            if (existingAssistant != null) {
                throw new RuntimeException("Există deja un asistent cu acest ID.");
            }

            // Creează asistent nou
            service.create(assistant);
            redirectAttributes.addFlashAttribute("message", "Asistent creat cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        return "redirect:/assistants";
    }

    // UPDATE (editare)
    @PostMapping("/update")
    public String updateAssistant(@ModelAttribute Assistant assistant,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        try {
            // Validare câmpuri
            AssistantValidator.validateAssistant(assistant);

            // Verifică că asistentul există
            Assistant existingAssistant = service.findById(assistant.getStaffID());
            if (existingAssistant == null) {
                throw new RuntimeException("Asistentul nu există.");
            }

            // Face update
            service.update(assistant.getStaffID(), assistant);
            redirectAttributes.addFlashAttribute("message", "Asistent actualizat cu succes!");

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        return "redirect:/assistants";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "Asistent șters cu succes!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/assistants";
    }
}