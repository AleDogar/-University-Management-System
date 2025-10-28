package com.example.University.Management.System.model;

import java.util.List;

public class Room {
    private String roomID;
    private double capacity;
    private String number;
    private List<Course> courses;

    public Room(String roomID, double capacity, String number, List<Course> courses) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.number = number;
        this.courses = courses;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
                ", capacity=" + capacity +
                ", number='" + number + '\'' +
                ", courses=" + courses +
                '}';
    }
}