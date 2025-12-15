package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id")
    @NotBlank(message = "ID cursului este obligatoriu")
    @Pattern(regexp = "^C\\d+$", message = "Format: C urmat de cifre (ex: C1, C101)")
    private String courseID;

    @NotBlank(message = "Titlul este obligatoriu")
    @Size(min = 2, max = 255, message = "Titlul trebuie să aibă între 2 și 255 caractere")
    private String title;

    @Min(value = 1, message = "Creditele trebuie să fie cel puțin 1")
    @Max(value = 30, message = "Creditele nu pot depăși 30")
    private int credits;

    @NotBlank(message = "Departament obligatoriu")
    private String departmentID;

    @NotBlank(message = "Sală obligatorie")
    private String roomID;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeachingAssignment> assignments = new ArrayList<>();

    public Course() {}

    public Course(String courseID, String title, int credits,
                  String departmentID, String roomID) {
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
        this.departmentID = departmentID;
        this.roomID = roomID;
    }

    // getters & setters

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public List<TeachingAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<TeachingAssignment> assignments) {
        this.assignments = assignments;
    }
}
