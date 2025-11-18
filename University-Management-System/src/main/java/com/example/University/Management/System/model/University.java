package com.example.University.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String universityID;
    private String universityName;
    private String city;
    private List<Department> departments;
    private List<Room> rooms;

    public University() {
        this.departments = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public University(String universityID, String universityName, String city) {
        this.universityID = universityID;
        this.universityName = universityName;
        this.city = city;
        this.departments = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public String getUniversityID() {
        return universityID;
    }

    public void setUniversityID(String universityID) {
        this.universityID = universityID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
