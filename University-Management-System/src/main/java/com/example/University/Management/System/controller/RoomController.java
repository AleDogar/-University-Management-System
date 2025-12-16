package com.example.University.Management.System.controller;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // ================= LISTARE cu SORTARE și FILTRARE =================
    @GetMapping
    public String listAll(@RequestParam(required = false) String sortBy,
                          @RequestParam(required = false) String sortDir,
                          @RequestParam(required = false) String filterRoomName,
                          @RequestParam(required = false) String filterMinCapacity,
                          Model model) {

        List<Room> rooms = roomService.findAllWithSortAndFilter(
                sortBy, sortDir, filterRoomName, filterMinCapacity);

        model.addAttribute("rooms", rooms);

        // Adăugăm parametrii pentru a-i păstra în formular
        model.addAttribute("sortBy", sortBy != null ? sortBy : "roomID");
        model.addAttribute("sortDir", sortDir != null ? sortDir : "asc");
        model.addAttribute("filterRoomName", filterRoomName != null ? filterRoomName : "");
        model.addAttribute("filterMinCapacity", filterMinCapacity != null ? filterMinCapacity : "");

        return "room/index";
    }

    // ================= DETAILS =================
    @GetMapping("/{id}")
    public String viewDetails(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Room room = roomService.findById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "room/details";
        }
        redirectAttributes.addFlashAttribute("error", "Sala nu există!");
        return "redirect:/rooms";
    }

    // ================= CREATE FORM =================
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("room", new Room());
        return "room/form";
    }

    // ================= EDIT FORM =================
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Room room = roomService.findById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "room/form";
        }
        redirectAttributes.addFlashAttribute("error", "Sala nu există!");
        return "redirect:/rooms";
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public String createRoom(@Valid @ModelAttribute("room") Room room,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        // 1. Verificăm validările de câmpuri (@NotBlank, @Size, etc.)
        if (bindingResult.hasErrors()) {
            return "room/form";
        }

        // 2. Business validation: Verificăm dacă ID-ul există deja
        if (roomService.findById(room.getRoomID()) != null) {
            bindingResult.rejectValue("roomID", "error.room",
                    "ID-ul sălii '" + room.getRoomID() + "' există deja! Alegeți un alt ID.");
            return "room/form";
        }

        // 3. Dacă toate validările au trecut, creăm sala
        boolean created = roomService.create(room);
        if (!created) {
            model.addAttribute("error", "Eroare la crearea sălii! Verificați datele introduse.");
            return "room/form";
        }

        redirectAttributes.addFlashAttribute("message", "Sală creată cu succes!");
        return "redirect:/rooms";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateRoom(@Valid @ModelAttribute("room") Room room,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        // 1. Verificăm validările de câmpuri
        if (bindingResult.hasErrors()) {
            return "room/form";
        }

        // 2. Actualizăm sala
        boolean updated = roomService.update(room.getRoomID(), room);
        if (!updated) {
            model.addAttribute("error", "Sala nu există pentru actualizare!");
            return "room/form";
        }

        redirectAttributes.addFlashAttribute("message", "Sală actualizată cu succes!");
        return "redirect:/rooms";
    }

    // ================= DELETE =================
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean deleted = roomService.delete(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("error",
                    "Nu se poate șterge sala! Sala nu există sau are cursuri asociate.");
            return "redirect:/rooms";
        }

        redirectAttributes.addFlashAttribute("message", "Sală ștearsă cu succes!");
        return "redirect:/rooms";
    }
}