package com.example.University.Management.System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    private String enrollmentID;

    private String studentID;
    private String courseID;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private ClassGrade grade;

    public Enrollment() {}

    public Enrollment(String enrollmentID, String studentID, String courseID, ClassGrade grade) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        if (student != null) {
            this.studentID = student.getId(); // Folosește getId()
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
        if (course != null) {
            this.courseID = course.getCourseID();
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
                ", studentID='" + studentID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", student=" + (student != null ? student.getName() : "null") +
                ", course=" + (course != null ? course.getTitle() : "null") +
                ", grade=" + grade +
                '}';
    }
}