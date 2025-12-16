package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @NotBlank(message = "ID Student este obligatoriu")
    @Pattern(regexp = "S\\d+", message = "ID-ul trebuie să înceapă cu 'S' urmat de număr, ex: S1")
    private String studentID;

    @NotBlank(message = "Numele studentului este obligatoriu")
    @Size(min = 2, max = 100, message = "Numele trebuie să aibă între 2 și 100 caractere")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Numele trebuie să conțină doar litere și spații")
    private String studentName;

    @NotBlank(message = "Email-ul este obligatoriu")
    @Email(message = "Email-ul trebuie să fie valid")
    private String email;

    public Student() {}

    public Student(String studentID, String studentName, String email) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.email = email;
    }

    // Getters & Setters
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}