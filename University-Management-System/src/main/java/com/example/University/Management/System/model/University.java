package com.example.University.Management.System.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "universities")
public class University {

    @Id
    private String universityID;

    private String universityName;

    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "university_id") // cheia străină în Department
    private List<Department> departments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "university_id") // cheia străină în Room
    private List<Room> rooms = new ArrayList<>();

    // Constructor gol
    public University() {}

    // Constructor cu parametri
    public University(String universityID, String universityName, String city) {
        this.universityID = universityID;
        this.universityName = universityName;
        this.city = city;
        this.departments = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    // Getters și setters
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

    @Override
    public String toString() {
        return "University{" +
                "universityID='" + universityID + '\'' +
                ", universityName='" + universityName + '\'' +
                ", city='" + city + '\'' +
                ", departments=" + departments +
                ", rooms=" + rooms +
                '}';
    }
}
