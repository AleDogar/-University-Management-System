package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    public void removeRoom(String roomId) {
        Room room = roomRepository.findById(roomId);
        if (room != null) {
            roomRepository.delete(room);
        }
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}