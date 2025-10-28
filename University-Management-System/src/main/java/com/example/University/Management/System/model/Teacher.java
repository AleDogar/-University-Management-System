package com.example.University.Management.System.model;

public class Teacher {
    private String title;
    private String departmentID;

    public Teacher(String title, String departmentID) {
        this.title = title;
        this.departmentID = departmentID;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "title='" + title + '\'' +
                ", departmentID='" + departmentID + '\'' +
                '}';
    }
}
