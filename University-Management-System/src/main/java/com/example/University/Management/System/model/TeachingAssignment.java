package com.example.University.Management.System.model;

public class TeachingAssignment {
    private String id;
    private String courseId;
    private String staffId;
    private ClassType classType;

    public TeachingAssignment() {}

    public TeachingAssignment(String id, ClassType classType, String courseId, String staffId) {
        this.id = id;
        this.classType = classType;
        this.courseId = courseId;
        this.staffId = staffId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    @Override
    public String toString() {
        return "TeachingAssignment{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", classType=" + classType +
                '}';
    }
}
