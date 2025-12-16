package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    // Creare sală
    public boolean create(Room room) {
        if (room.getRoomID() != null) {
            room.setRoomID(room.getRoomID().trim());
        }
        if (room.getRoomName() != null) {
            room.setRoomName(room.getRoomName().trim());
        }

        System.out.println("Creating Room with ID: " + room.getRoomID());

        if (repository.existsById(room.getRoomID())) {
            System.out.println("ID already exists: " + room.getRoomID());
            return false;
        }

        repository.save(room);
        System.out.println("Room saved: " + room);
        return true;
    }

    // Obținerea tuturor sălilor (pentru dropdowns sau alte nevoi)
    public Map<String, Room> findAll() {
        List<Room> list = repository.findAll();
        Map<String, Room> map = new HashMap<>();
        for (Room room : list) {
            map.put(room.getRoomID(), room);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru săli (NOU)
    public List<Room> findAllWithSortAndFilter(String sortBy, String sortDir,
                                               String filterRoomName, String filterMinCapacity) {

        List<Room> rooms;

        // Transformăm string-urile goale în null pentru query
        String nameParam = (filterRoomName != null && !filterRoomName.trim().isEmpty()) ?
                filterRoomName.trim() : null;

        Integer capacityParam = null;
        if (filterMinCapacity != null && !filterMinCapacity.trim().isEmpty()) {
            try {
                capacityParam = Integer.parseInt(filterMinCapacity.trim());
            } catch (NumberFormatException e) {
                // Dacă nu e număr valid, ignorăm filtrul
                capacityParam = null;
            }
        }

        // FILTRARE folosind metoda principală
        rooms = repository.findByFilters(nameParam, capacityParam);

        // SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Room> comparator = getComparator(sortBy, sortDir);
            rooms = rooms.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return rooms;
    }

    // Comparator pentru sortare (NOU)
    private Comparator<Room> getComparator(String sortBy, String sortDir) {
        Comparator<Room> comparator;

        switch (sortBy) {
            case "roomID":
                comparator = Comparator.comparing(Room::getRoomID);
                break;
            case "roomName":
                comparator = Comparator.comparing(Room::getRoomName,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "capacity":
                comparator = Comparator.comparing(Room::getCapacity);
                break;
            default:
                comparator = Comparator.comparing(Room::getRoomID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
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