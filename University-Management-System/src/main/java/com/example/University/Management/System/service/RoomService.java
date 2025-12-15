package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    // Creare sală
    public boolean create(Room room) {
        // Trim ID și alte câmpuri pentru siguranță
        if (room.getRoomID() != null) {
            room.setRoomID(room.getRoomID().trim());
        }
        if (room.getRoomName() != null) {
            room.setRoomName(room.getRoomName().trim());
        }

        // Business validation: ID unic
        if (repository.existsById(room.getRoomID())) {
            System.out.println("ID already exists: " + room.getRoomID());
            return false;
        }

        repository.save(room);
        System.out.println("Room saved: " + room);
        return true;
    }

    // Obținerea tuturor sălilor
    public Map<String, Room> findAll() {
        List<Room> list = repository.findAll();
        Map<String, Room> map = new HashMap<>();
        for (Room room : list) {
            map.put(room.getRoomID(), room);
        }
        return map;
    }

    // Obținere sală după ID
    public Room findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update sală
    public boolean update(String id, Room room) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Room ID does not exist -> " + id);
            return false;
        }

        // Trim câmpuri
        room.setRoomID(id.trim());
        if (room.getRoomName() != null) {
            room.setRoomName(room.getRoomName().trim());
        }

        repository.save(room);
        System.out.println("Room updated: " + room);
        return true;
    }

    // Ștergere sală
    public boolean delete(String id) {
        if (id == null) return false;

        Room room = repository.findById(id.trim()).orElse(null);
        if (room == null) {
            System.out.println("Delete failed: Room ID not found -> " + id);
            return false;
        }

        // Business validation: nu putem șterge săli care au cursuri
        if (room.getCourses() != null && !room.getCourses().isEmpty()) {
            System.out.println("Delete failed: Room has courses -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Room deleted: " + id);
        return true;
    }
}