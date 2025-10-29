package com.example.University.Management.System.model;

import java.util.List;

public class Assistant extends Staff {
    private ClassRole role;

    public Assistant(ClassRole role, String staffName, String staffID, List<TeachingAssignment> assignments) {
        super(assignments, staffName, staffID);
        this.role = role;
    }

    public ClassRole getRole() {
        return role;
    }

    public void setRole(ClassRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "role=" + role +
                ", staffName='" + getStaffName() + '\'' +
                ", staffID='" + getStaffID() + '\'' +
                '}';
    }
}
