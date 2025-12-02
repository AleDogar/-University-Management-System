package com.example.University.Management.System.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String courseID;

    private String title;
    private int credits;
    private String departmentID;
    private String roomID;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
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

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
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
                ", enrollments=" + enrollments +
                ", assignments=" + assignments +
                '}';
    }
}
