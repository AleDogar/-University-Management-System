package com.example.University.Management.System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    private String enrollmentID;

    // Păstrăm courseID ca field simplu
    private String courseID;

    // Relație ManyToOne cu Course, folosim insertable=false, updatable=false
    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private ClassGrade grade;

    public Enrollment() {}

    public Enrollment(String enrollmentID, String courseID, ClassGrade grade) {
        this.enrollmentID = enrollmentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    // Getters & Setters
    public String getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(String enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        if(course != null) {
            this.courseID = course.getCourseID(); // sincronizează ID-ul
        }
    }

    public ClassGrade getGrade() {
        return grade;
    }

    public void setGrade(ClassGrade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentID='" + enrollmentID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", course=" + (course != null ? course.getCourseID() : "null") +
                ", grade=" + grade +
                '}';
    }
}
