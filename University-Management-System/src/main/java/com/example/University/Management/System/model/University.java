package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "universities")
public class University {

    @Id
    @NotBlank(message = "ID Universitate este obligatoriu")
    @Pattern(regexp = "U\\d+", message = "ID-ul trebuie să înceapă cu 'U' urmat de număr, ex: U1")
    private String universityID;

    @NotBlank(message = "Numele universității este obligatoriu")
    @Size(min = 2, max = 200, message = "Numele trebuie să aibă între 2 și 200 caractere")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Numele trebuie să conțină doar litere și spații")
    private String universityName;

    @NotBlank(message = "Orașul este obligatoriu")
    @Size(min = 2, max = 100, message = "Orașul trebuie să aibă între 2 și 100 caractere")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Orașul trebuie să conțină doar litere și spații")
    private String city;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "university_id")
    private List<Department> departments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "university_id")
    private List<Room> rooms = new ArrayList<>();

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
