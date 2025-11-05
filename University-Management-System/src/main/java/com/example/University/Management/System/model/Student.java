package com.example.University.Management.System.model;

import java.util.List;

public class Student {
    private String studentID;
    private String studentName;
    private List<Enrollment> enrollments;
    private String email;

    public Student(List<Enrollment> enrollments, String studentName, String studentID, String email) {
        this.enrollments = enrollments;
        this.studentName = studentName;
        this.studentID = studentID;
        this.email = email;
    }

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

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
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
                ", enrollments=" + enrollments +
                ", email='" + email + '\'' +
                '}';
    }
}
