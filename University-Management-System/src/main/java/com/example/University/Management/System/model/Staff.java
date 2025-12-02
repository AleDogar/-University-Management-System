package com.example.University.Management.System.model;

import jakarta.persistence.*;
import java.util.List;

@MappedSuperclass
public abstract class Staff {

    @Id
    @Column(name = "id")
    protected String staffID;

    @Column(name = "name")
    protected String staffName;


    @Transient  // relatia se implementeaza in Teacher si Assistant
    protected List<TeachingAssignment> assignments;

    public Staff() {}

    public Staff(String staffID, String staffName) {
        this.staffID = staffID;
        this.staffName = staffName;
    }

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

    public List<TeachingAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<TeachingAssignment> assignments) {
        this.assignments = assignments;
    }
}
