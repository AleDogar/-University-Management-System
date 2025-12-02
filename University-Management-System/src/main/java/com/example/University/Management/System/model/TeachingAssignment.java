package com.example.University.Management.System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

@Entity
public class TeachingAssignment {
    @Id
    private String id;

    @Column(name = "course_id", insertable = false, updatable = false)
    private String courseId;

    private String staffId;
    @Enumerated(EnumType.STRING)
    private ClassType classType;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //Constructorul
    public TeachingAssignment() {}

    public TeachingAssignment(String id, ClassType classType, String courseId, String staffId) {
        this.id = id;
        this.courseId = courseId;
        this.staffId = staffId;
        this.classType = classType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        if(course != null) {
            this.courseId = course.getCourseID(); // synchronize id
        }
    }

    @Override
    public String toString() {
        return "TeachingAssignment{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", classType=" + classType +
                '}';
    }
}
