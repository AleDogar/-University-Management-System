package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        if (room.getRoomID() == null || room.getRoomID().isEmpty()) {
            room.setRoomID(UUID.randomUUID().toString());
        }
        roomRepository.save(room);
        return room;
    }

    public void removeRoom(String id) {
        roomRepository.deleteById(id);
    }
}
