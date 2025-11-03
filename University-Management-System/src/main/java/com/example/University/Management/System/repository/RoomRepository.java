package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository extends InMemoryRepository<Room> {
    @Override
    protected String getId(Room entity) { return entity.getRoomID(); }
    @Override
    protected void setId(Room entity, String id) { entity.setRoomID(id); }
}
