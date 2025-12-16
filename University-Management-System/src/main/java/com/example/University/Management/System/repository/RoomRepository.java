package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    // Metode individuale simple
    List<Room> findByRoomNameContainingIgnoreCase(String roomName);
    List<Room> findByCapacityGreaterThanEqual(Integer minCapacity);

    // Metoda de filtrare avansată pentru pagina principală
    @Query("SELECT r FROM Room r WHERE " +
            "(:roomName IS NULL OR LOWER(r.roomName) LIKE LOWER(CONCAT('%', :roomName, '%'))) AND " +
            "(:minCapacity IS NULL OR r.capacity >= :minCapacity)")
    List<Room> findByFilters(
            @Param("roomName") String roomName,
            @Param("minCapacity") Integer minCapacity);
}