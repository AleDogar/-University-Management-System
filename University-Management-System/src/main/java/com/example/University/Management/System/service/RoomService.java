package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RoomService {


    private RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public boolean create(Room assignment) {
        return repository.create(assignment);
    }

    public Map<String, Room> findAll() {
        return repository.findAll();
    }

    public Room findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, Room room) {
        return repository.update(id, room);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}