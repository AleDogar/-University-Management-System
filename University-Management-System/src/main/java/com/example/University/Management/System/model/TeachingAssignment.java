package com.example.University.Management.System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teaching_assignments")
public class TeachingAssignment {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassType classType;

    @Column(name = "staff_id", nullable = false)
    private String staffId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    public TeachingAssignment() {}

    public TeachingAssignment(String id, ClassType classType, Course course, String staffId) {
        this.id = id;
        this.classType = classType;
        this.course = course;
        this.staffId = staffId;
    }

    // getters & setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
