package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @NotBlank(message = "ID-ul sălii este obligatoriu")
    @Pattern(regexp = "R\\d+", message = "ID-ul trebuie să înceapă cu 'R' urmat de număr, ex: R1")
    private String roomID;

    @NotBlank(message = "Numărul/numele sălii este obligatoriu")
    @Size(min = 1, max = 100, message = "Numărul/numele trebuie să aibă între 1 și 100 caractere")
    private String roomName;

    @NotNull(message = "Capacitatea este obligatorie")
    @Min(value = 1, message = "Capacitatea trebuie să fie cel puțin 1")
    @Max(value = 500, message = "Capacitatea nu poate depăși 500")
    private Integer capacity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private List<Course> courses = new ArrayList<>();

    public Room() {}

    public Room(String roomID, String roomName, Integer capacity) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.capacity = capacity;
        this.courses = new ArrayList<>();
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID='" + roomID + '\'' +
                ", roomName='" + roomName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}