package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.model.ClassRole;
import com.example.University.Management.System.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assistants")
public class AssistantController {

    private final AssistantService service;

    @Autowired
    public AssistantController(AssistantService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("assistants", service.findAll());
        model.addAttribute("roles", ClassRole.values());
        return "assistant/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("assistant", new Assistant());
        model.addAttribute("roles", ClassRole.values());
        return "assistant/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Assistant a = service.findById(id);
        if (a != null) {
            model.addAttribute("assistant", a);
            model.addAttribute("roles", ClassRole.values());
            return "assistant/form";
        }
        return "redirect:/assistants";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Assistant assistant) {
        service.create(assistant);
        return "redirect:/assistants";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/assistants";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Assistant a = service.findById(id);
        if (a != null) {
            model.addAttribute("assistant", a);
            return "assistant/details"; // vei crea view-ul details similar cu University/Teacher
        }
        return "redirect:/assistants";
    }
}
