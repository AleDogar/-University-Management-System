package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository extends InMemoryRepository<Room> {

    @Override
    protected String getId(Room room) {
        return room.getRoomID();
    }

    @Override
    protected void setId(Room room, String id) {
        room.setRoomID(id);
    }
}