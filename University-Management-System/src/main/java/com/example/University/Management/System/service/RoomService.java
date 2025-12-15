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

    public boolean create(Room room) {
        if (repository.existsById(room.getRoomID())) {
            return false;
        }
        repository.save(room);
        return true;
    }

    public Map<String, Room> findAll() {
        List<Room> list = repository.findAll();
        Map<String, Room> map = new HashMap<>();
        for (Room room : list) {
            map.put(room.getRoomID(), room);
        }
        return map;
    }

    public Room findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, Room room) {
        if (!repository.existsById(id)) {
            return false;
        }
        room.setRoomID(id);
        repository.save(room);
        return true;
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}