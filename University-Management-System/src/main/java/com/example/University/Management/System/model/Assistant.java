package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "assistants")
public class Assistant extends Staff {

    @NotNull(message = "Rolul este obligatoriu")
    @Enumerated(EnumType.STRING)
    private ClassRole role;

    @NotBlank(message = "Email-ul este obligatoriu")
    @Email(message = "Email-ul trebuie să fie valid")
    private String email;

    public Assistant() {
        super();
    }

    public Assistant(String staffID, String staffName, ClassRole role, String email) {
        super(staffID, staffName);
        this.role = role;
        this.email = email;
    }

    // Validare specifică pentru Assistant
    @Override
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

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
                "staffID='" + getStaffID() + '\'' +
                ", staffName='" + getStaffName() + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                '}';
    }
}