package com.example.University.Management.System.model;

public class Assistant {
    private ClassRole role;
    private String staffID;

    public Assistant(ClassRole role, String staffID) {
        this.role = role;
        this.staffID = staffID;
    }

    public ClassRole getRole() {
        return role;
    }

    public void setRole(ClassRole role) {
        this.role = role;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "role=" + role +
                ", staffID='" + staffID + '\'' +
                '}';
    }
}
