package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.service.AssistantService;
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

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Assistant assistant = service.findById(id);
        if (assistant != null) {
            model.addAttribute("assistant", assistant);
            return "assistant/details";
        }
        return "redirect:/assistants";
    }

    @PostMapping("/create")
    public String createAssistant(@ModelAttribute Assistant assistant, RedirectAttributes redirectAttributes) {
        service.create(assistant);
        redirectAttributes.addFlashAttribute("message", "Asistent creat cu succes!");
        return "redirect:/assistants";
    }

    @PostMapping("/update")
    public String updateAssistant(@ModelAttribute Assistant assistant, RedirectAttributes redirectAttributes) {
        service.update(assistant.getStaffID(), assistant);
        redirectAttributes.addFlashAttribute("message", "Asistent actualizat cu succes!");
        return "redirect:/assistants";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Asistent șters cu succes!");
        return "redirect:/assistants";
    }
}