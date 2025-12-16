package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Staff {

    @Id
    @Column(name = "id")
    @NotBlank(message = "ID Staff este obligatoriu")
    protected String staffID;

    @Column(name = "name")
    @NotBlank(message = "Numele este obligatoriu")
    @Size(min = 2, max = 100, message = "Numele trebuie să aibă între 2 și 100 caractere")
    protected String staffName;

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
}