package com.example.University.Management.System.model;

import java.util.List;

public class Room {
    private String roomID;
    private String number;
    private double capacity;
    private List<Course> courses;

    // Constructor corect: number înaintea capacity
    public Room(String roomID, String number, double capacity, List<Course> courses) {
        this.roomID = roomID;
        this.number = number;
        this.capacity = capacity;
        this.courses = courses;
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
