package com.example.University.Management.System.model;

import java.util.List;

public class Student {
    private String studentID;
    private String studentName;
    private List<Enrollment> enrollments;

    public Student(String studentID, String studentName, List<Enrollment> enrollments) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.enrollments = enrollments;
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

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", enrollments=" + enrollments +
                '}';
    }
}
