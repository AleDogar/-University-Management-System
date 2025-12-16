package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends Staff {

    @NotBlank(message = "Titlul este obligatoriu")
    private String title;

    @NotBlank(message = "Email-ul este obligatoriu")
    @Email(message = "Email-ul trebuie să fie valid")
    private String email;

    @NotBlank(message = "ID-ul departamentului este obligatoriu")
    @Pattern(regexp = "D\\d+", message = "ID-ul departamentului trebuie să înceapă cu 'D' urmat de număr, ex: D1")
    private String departmentID;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private List<TeachingAssignment> assignments;

    public Teacher() {
        super();
        assignments = new ArrayList<>();
    }

    public Teacher(String staffID, String staffName, String title, String departmentID, String email) {
        super(staffID, staffName);
        this.title = title;
        this.departmentID = departmentID;
        this.email = email;
        this.assignments = new ArrayList<>();
    }

    // Adaugă validare specifică pentru Teacher în constructor
    @Override
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TeachingAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<TeachingAssignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "staffID='" + staffID + '\'' +
                ", staffName='" + staffName + '\'' +
                ", title='" + title + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}