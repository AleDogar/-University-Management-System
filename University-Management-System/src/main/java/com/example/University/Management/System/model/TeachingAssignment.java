package com.example.University.Management.System.model;

public class TeachingAssignment {
    private String teachingassignmentID;
    private String courseID;
    private String StaffID;
    private ClassType managing;

    public TeachingAssignment(String teachingassignmentID, ClassType managing, String courseID, String staffID) {
        this.teachingassignmentID = teachingassignmentID;
        this.managing = managing;
        this.courseID = courseID;
        this.StaffID = staffID;
    }

    public String getTeachingassignmentID() {
        return teachingassignmentID;
    }

    public void setTeachingassignmentID(String teachingassignmentID) {
        this.teachingassignmentID = teachingassignmentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    public ClassType getManaging() {
        return managing;
    }

    public void setManaging(ClassType managing) {
        this.managing = managing;
    }

    @Override
    public String toString() {
        return "TeachingAssignment{" +
                "teachingassignmentID='" + teachingassignmentID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", StaffID='" + StaffID + '\'' +
                ", managing=" + managing +
                '}';
    }
}
