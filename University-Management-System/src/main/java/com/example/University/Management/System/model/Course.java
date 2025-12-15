package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @NotBlank(message = "ID cursului este obligatoriu")
    @Pattern(regexp = "C\\d+", message = "ID-ul trebuie să înceapă cu 'C' urmat de număr, ex: C1")
    private String courseID;

    @NotBlank(message = "Titlul este obligatoriu")
    @Size(min = 2, max = 255, message = "Titlul trebuie să aibă între 2 și 255 caractere")
    private String title;

    @NotNull(message = "Creditele sunt obligatorii")
    @Min(value = 1, message = "Creditele trebuie să fie cel puțin 1")
    @Max(value = 30, message = "Creditele nu pot depăși 30")
    private Integer credits;

    @NotBlank(message = "Departamentul este obligatoriu")
    private String departmentID;

    @NotBlank(message = "Sala este obligatorie")
    private String roomID;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<TeachingAssignment> assignments = new ArrayList<>();

    public Course() {}

    public Course(String courseID, String title, Integer credits,
                  String departmentID, String roomID) {
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
        this.departmentID = departmentID;
        this.roomID = roomID;
        this.assignments = new ArrayList<>();
    }

    // Getters & Setters
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

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
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

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", departmentID='" + departmentID + '\'' +
                ", roomID='" + roomID + '\'' +
                '}';
    }
}