package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class RoomRepository extends InFileRepository<Room> {

    public RoomRepository(ObjectMapper mapper,
                          @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "rooms.json");
    }

    @Override
    protected String getId(Room entity) {
        return entity.getRoomID();
    }

    @Override
    protected void setId(Room entity, String id) {
        entity.setRoomID(id);
    }
}
