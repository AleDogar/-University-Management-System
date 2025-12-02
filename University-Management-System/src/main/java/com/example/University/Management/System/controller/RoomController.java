package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("rooms", service.findAll());
        return "room/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("room", new Room(null, "", 0.0));
        return "room/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Room room = service.findById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "room/form";
        }
        return "redirect:/rooms";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Room room) {
        service.create(room);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/rooms";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model) {
        Room room = service.findById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "room/details";
        }
        return "redirect:/rooms";
    }
}
