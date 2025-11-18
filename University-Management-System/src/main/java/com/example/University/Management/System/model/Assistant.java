package com.example.University.Management.System.model;

import java.util.List;

public class Assistant extends Staff {
    private ClassRole role;
    private String email;
    private String departmentID;

    // Constructor cu parametri
    public Assistant(ClassRole role, String staffName, String staffID, List<TeachingAssignment> assignments) {
        super(staffName, staffID, assignments);
        this.role = role;
    }

    // Constructor fără parametri
    public Assistant() {}

    public ClassRole getRole() { return role; }
    public void setRole(ClassRole role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartmentID() { return departmentID; }
    public void setDepartmentID(String departmentID) { this.departmentID = departmentID; }

    @Override
    public String toString() {
        return "Assistant{" +
                "role=" + role +
                ", staffName='" + getStaffName() + '\'' +
                ", staffID='" + getStaffID() + '\'' +
                ", email='" + email + '\'' +
                ", departmentID='" + departmentID + '\'' +
                '}';
    }
}
