package com.example.University.Management.System.model;

public class Teacher {
    private String title;
    private String departmentID;
    private String teacherName;
    private String email;

    public Teacher(String title, String departmentID, String teacherName, String email) {
        this.title = title;
        this.departmentID = departmentID;
        this.teacherName = teacherName;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "title='" + title + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
