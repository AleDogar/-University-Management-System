package com.example.University.Management.System.model;

import java.util.List;

public class Department {
    private String departmentID;
    private String departmentName;
    private List<Course> courses;
    private List<Teacher> teachers;
    private String phoneNumber;

    public Department(String departmentID, String departmentName, List<Course> courses, List<Teacher> teachers, String phoneNumber) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
                ", departamentName='" + departmentName + '\'' +
                ", courses=" + courses +
                ", teachers=" + teachers +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

