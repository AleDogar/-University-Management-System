package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @NotBlank(message = "ID Departament este obligatoriu")
    @Pattern(regexp = "D\\d+", message = "ID-ul trebuie să înceapă cu 'D' urmat de număr, ex: D1")
    private String departmentID;

    @NotBlank(message = "Numele departamentului este obligatoriu")
    @Size(min = 2, max = 100, message = "Numele trebuie să aibă între 2 și 100 caractere")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Numele trebuie să conțină doar litere și spații")
    private String departmentName;

    @NotBlank(message = "Numărul de telefon este obligatoriu")
    @Pattern(regexp = "07\\d{8}", message = "Numărul de telefon trebuie să fie format din 07 urmat de 8 cifre")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private List<Course> courses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private List<Teacher> teachers = new ArrayList<>();

    public Department() {}

    public Department(String departmentID, String departmentName, String phoneNumber) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.phoneNumber = phoneNumber;
        this.courses = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    // Getters & Setters
    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID='" + departmentID + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", courses=" + courses +
                ", teachers=" + teachers +
                '}';
    }
}