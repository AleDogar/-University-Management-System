package com.example.University.Management.System.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends Staff {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private List<TeachingAssignment> assignments;
    private String title;
    private String email;
    private String departmentID;

    public Teacher() {

        assignments = new ArrayList<>();
    }

    public Teacher(String id, String name, String title, String departmentID, String email) {
        super(id, name);     // folosim datele din Staff
        this.title = title;
        this.departmentID = departmentID;
        this.email = email;
        this.assignments = new ArrayList<>();
    }

    @Override
    public String getStaffID() {
        return staffID;
    }
    @Override
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    @Override
    public String getStaffName() {
        return staffName;
    }
    @Override
    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
}
