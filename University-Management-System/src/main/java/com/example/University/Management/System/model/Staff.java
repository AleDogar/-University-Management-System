package com.example.University.Management.System.model;

import java.util.List;

public abstract class Staff {
    private String staffID;
    private String staffName;
    private List<TeachingAssignment> assignments;

    // Constructor cu parametri
    public Staff(String staffName, String staffID, List<TeachingAssignment> assignments) {
        this.staffName = staffName;
        this.staffID = staffID;
        this.assignments = assignments;
    }

    // Constructor fără parametri (pentru JSON / Thymeleaf)
    public Staff() {}

    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }

    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }

    public List<TeachingAssignment> getAssignments() { return assignments; }
    public void setAssignments(List<TeachingAssignment> assignments) { this.assignments = assignments; }

    @Override
    public String toString() {
        return "Staff{" +
                "staffID='" + staffID + '\'' +
                ", staffName='" + staffName + '\'' +
                ", assignments=" + assignments +
                '}';
    }
}
