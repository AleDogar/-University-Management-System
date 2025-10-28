package com.example.University.Management.System.model;

import java.util.List;

public class Department {
    private String departmentID;
    private String departamentName;
    private List<Course> courses;
    private List<Teacher> teachers;
    private String phoneNumber;

    public Department(String departmentID, String departamentName, List<Course> courses, List<Teacher> teachers, String phoneNumber) {
        this.departmentID = departmentID;
        this.departamentName = departamentName;
        this.courses = courses;
        this.teachers = teachers;
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartamentName() {
        return departamentName;
    }

    public void setDepartamentName(String departamentName) {
        this.departamentName = departamentName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID='" + departmentID + '\'' +
                ", departamentName='" + departamentName + '\'' +
                ", courses=" + courses +
                ", teachers=" + teachers +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

