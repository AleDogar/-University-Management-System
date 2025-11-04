package com.example.University.Management.System.model;

import java.util.List;

public class Teacher extends Staff {

    private String title;
    private String departmentID;
    private String email;

    public Teacher(List<TeachingAssignment> assignments, String staffName, String staffID) {
        super(assignments, staffName, staffID);
        this.title = title;
        this.departmentID = departmentID;
        this.email = email;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "title='" + title + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", email='" + email + '\'' +
                ", staffID='" + getStaffID() + '\'' +
                ", staffName='" + getStaffName() + '\'' +
                ", assignments=" + getAssignments() +
                '}';
    }
}
