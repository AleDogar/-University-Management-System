package com.example.University.Management.System.model;

public class Enrollment {
    private String enrollmentID;
    private String courseID;
    private ClassGrade grade;

    public Enrollment() {}

    public Enrollment(String enrollmentID, String courseID, ClassGrade grade) {
        this.enrollmentID = enrollmentID;
        this.courseID = courseID;
        this.grade = grade;
    }

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
                ", grade=" + grade +
                '}';
    }
}
