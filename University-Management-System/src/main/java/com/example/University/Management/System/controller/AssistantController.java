package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.model.ClassRole;
import com.example.University.Management.System.service.AssistantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assistants")
public class AssistantController {
    private final AssistantService service;
    public AssistantController(AssistantService service) { this.service = service; }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("assistants", service.getAllAssistants());
        model.addAttribute("roles", ClassRole.values());
        return "assistant/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assistant", new Assistant(ClassRole.Lab, "", "", null));
        model.addAttribute("roles", ClassRole.values());
        return "assistant/form";
    }

    @PostMapping
    public String add(@ModelAttribute Assistant a) {
        service.addAssistant(a);
        return "redirect:/assistants";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeAssistant(id);
        return "redirect:/assistants";
    }
}