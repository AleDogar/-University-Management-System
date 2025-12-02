package com.example.University.Management.System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class TeachingAssignment {
    @Id
    private String id;
    private String courseId;
    private String staffId;
    @Enumerated(EnumType.STRING)
    private ClassType classType;

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
