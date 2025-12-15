package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "teaching_assignments")
public class TeachingAssignment {

    @Id
    @NotBlank(message = "ID Asignare este obligatoriu")
    @Pattern(regexp = "TA\\d+", message = "ID-ul trebuie să înceapă cu 'TA' urmat de număr, ex: TA1")
    private String assignmentID;

    @NotNull(message = "Tipul clasei este obligatoriu")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassType classType;

    @NotBlank(message = "ID Profesor este obligatoriu")
    @Column(name = "staff_id", nullable = false)
    private String staffID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    public TeachingAssignment() {}

    public TeachingAssignment(String assignmentID, ClassType classType, Course course, String staffID) {
        this.assignmentID = assignmentID;
        this.classType = classType;
        this.course = course;
        this.staffID = staffID;
    }

    // Getters & Setters
    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TeachingAssignment{" +
                "assignmentID='" + assignmentID + '\'' +
                ", classType=" + classType +
                ", staffID='" + staffID + '\'' +
                ", courseID=" + (course != null ? course.getCourseID() : "null") +
                '}';
    }
}