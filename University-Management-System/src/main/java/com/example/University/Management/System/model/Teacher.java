package com.example.University.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Staff {

    private String staffID;
    private String staffName;
    private String title;
    private String departmentID;
    private String email;
    private List<TeachingAssignment> assignments;

    // Constructor gol necesar pentru Jackson/Thymeleaf
    public Teacher() {
        this.assignments = new ArrayList<>();
    }

    // Constructor cu parametri
    public Teacher(String staffID, String staffName, String title, String departmentID, String email) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.title = title;
        this.departmentID = departmentID;
        this.email = email;
        this.assignments = new ArrayList<>();
    }

    // Getter & Setter
    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

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

    @Override
    public String toString() {
        return "Teacher{" +
                "staffID='" + staffID + '\'' +
                ", staffName='" + staffName + '\'' +
                ", title='" + title + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", email='" + email + '\'' +
                ", assignments=" + assignments +
                '}';
    }
}
