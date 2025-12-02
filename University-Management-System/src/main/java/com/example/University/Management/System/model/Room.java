package com.example.University.Management.System.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    private String roomID;

    private String number;

    private double capacity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id") // coloana în Course care va referi Room-ul
    private List<Course> courses = new ArrayList<>();

    public Room() {}

    public Room(String roomID, String number, double capacity) {
        this.roomID = roomID;
        this.number = number;
        this.capacity = capacity;
        this.courses = new ArrayList<>();
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
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
                ", number='" + number + '\'' +
                ", capacity=" + capacity +
                ", courses=" + courses +
                '}';
    }
}
