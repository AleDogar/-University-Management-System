package com.example.University.Management.System.model;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.model.Room;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String universityID;
    private String universityName;
    private String city;
    private List<Department> departments;
    private List<Room> rooms;

    public University(String id, String name, String city) {
        this.universityID = id;
        this.universityName = name;
        this.city = city;
        this.departments = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public String getId() {
        return universityID;
    }

    public void setId(String id) {
        this.universityID = id;
    }

    public String getName() {
        return universityName;
    }

    public void setName(String name) {
        this.universityName = name;
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

    @Override
    public String toString() {
        return "University{" +
                "id='" + universityID + '\'' +
                ", name='" + universityName + '\'' +
                ", city='" + city + '\'' +
                ", departments=" + departments +
                ", rooms=" + rooms +
                '}';
    }
}
