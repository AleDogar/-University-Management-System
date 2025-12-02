package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Room;
import com.example.University.Management.System.repository.interfaces.RoomJpaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

//@Profile("infile")
@Repository

public class RoomRepository extends DatabaseRepository<Room> {
    protected RoomRepository(RoomJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected String getIdFromEntity(Room entity) {
        return entity.getRoomID();
    }
}
