package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.service.AssistantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/assistants")
public class AssistantController {

    private final AssistantService assistantService;

    public AssistantController(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    // Listare asistenți
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("assistants", assistantService.findAll());
        return "assistant/index";
    }

    // Detalii asistent
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

    // Formular adăugare asistent
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assistant", new Assistant());
        return "assistant/form";
    }

    // Formular editare asistent
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

    // Creare asistent
    @PostMapping("/create")
    public String createAssistant(@Valid @ModelAttribute("assistant") Assistant assistant,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        // 1. Verificăm mai întâi erorile de validare din model
        if (bindingResult.hasErrors()) {
            return "assistant/form";
        }

        // 2. Business validation: Verificăm dacă ID-ul există deja
        if (assistantService.findById(assistant.getStaffID()) != null) {
            model.addAttribute("error", "ID-ul asistentului '" + assistant.getStaffID() + "' există deja! Alegeți un alt ID.");
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        // 3. Creăm asistentul
        boolean created = assistantService.create(assistant);
        if (!created) {
            model.addAttribute("error", "Eroare la crearea asistentului! Verificați datele introduse.");
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        redirectAttributes.addFlashAttribute("message", "Asistent creat cu succes!");
        return "redirect:/assistants";
    }

    // Actualizare asistent
    @PostMapping("/update")
    public String updateAssistant(@Valid @ModelAttribute("assistant") Assistant assistant,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        // 1. Verificăm erorile de validare
        if (bindingResult.hasErrors()) {
            return "assistant/form";
        }

        // 2. Actualizăm asistentul
        boolean updated = assistantService.update(assistant.getStaffID(), assistant);
        if (!updated) {
            model.addAttribute("error", "Asistentul nu există pentru actualizare!");
            model.addAttribute("assistant", assistant);
            return "assistant/form";
        }

        redirectAttributes.addFlashAttribute("message", "Asistent actualizat cu succes!");
        return "redirect:/assistants";
    }

    // Ștergere asistent
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