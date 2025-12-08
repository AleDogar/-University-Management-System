package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("rooms", service.findAll());
        return "room/index";
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

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("room", new Room());
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
    public String createRoom(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {
        service.create(room);
        redirectAttributes.addFlashAttribute("message", "Sală creată cu succes!");
        return "redirect:/rooms";
    }

    @PostMapping("/update")
    public String updateRoom(@ModelAttribute Room room, RedirectAttributes redirectAttributes) {
        service.update(room.getRoomID(), room);
        redirectAttributes.addFlashAttribute("message", "Sală actualizată cu succes!");
        return "redirect:/rooms";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Sală ștearsă cu succes!");
        return "redirect:/rooms";
    }
}
