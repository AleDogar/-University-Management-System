package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.model.University;
import com.example.University.Management.System.service.RoomService;
import com.example.University.Management.System.service.UniversityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("rooms", service.getAllRooms());
        return "room/index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("room", new Room(null, 0.0, "", null));
        return "room/form";
    }



    @PostMapping
    public String add(@ModelAttribute Room r) {
        service.addRoom(r);
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.removeRoom(id);
        return "redirect:/rooms";
    }
}


