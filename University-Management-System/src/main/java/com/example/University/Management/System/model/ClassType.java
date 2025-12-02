package com.example.University.Management.System.model;

public enum ClassType {
    LAB("Lab"),
    SEMINARY("Seminary"),
    COURSE("Course");

    private final String typeName;

    ClassType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
