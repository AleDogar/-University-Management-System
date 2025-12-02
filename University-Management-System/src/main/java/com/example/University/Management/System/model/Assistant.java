package com.example.University.Management.System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assistants")
public class Assistant extends Staff {

    @Enumerated(EnumType.STRING)
    private ClassRole role;

    private String email;

    public Assistant() {
        super();
    }

    public Assistant(String id, String name, ClassRole role, String email) {
        super(id, name);
        this.role = role;
        this.email = email;
    }

    // Getters & Setters
    public ClassRole getRole() {
        return role;
    }

    public void setRole(ClassRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "role=" + role +
                ", staffName='" + getStaffName() + '\'' +
                ", staffID='" + getStaffID() + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
