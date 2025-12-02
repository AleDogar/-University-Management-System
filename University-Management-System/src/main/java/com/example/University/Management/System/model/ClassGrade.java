package com.example.University.Management.System.model;

public enum ClassGrade {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    F("F"),
    NA("Not Assigned");

    private final String typeName;

    ClassGrade(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
