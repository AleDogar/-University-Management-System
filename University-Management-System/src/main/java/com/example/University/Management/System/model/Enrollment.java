package com.example.University.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @NotBlank(message = "ID Înscriere este obligatoriu")
    @Pattern(regexp = "E\\d+", message = "ID-ul trebuie să înceapă cu 'E' urmat de număr, ex: E1")
    private String enrollmentID;

    @NotBlank(message = "Student ID este obligatoriu")
    private String studentID;

    @NotBlank(message = "Course ID este obligatoriu")
    private String courseID;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @NotNull(message = "Nota este obligatorie")
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
            this.studentID = student.getStudentID();
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
                ", student=" + (student != null ? student.getStudentName() : "null") +
                ", course=" + (course != null ? course.getTitle() : "null") +
                ", grade=" + grade +
                '}';
    }
}