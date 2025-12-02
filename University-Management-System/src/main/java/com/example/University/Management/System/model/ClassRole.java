package com.example.University.Management.System.model;

public enum ClassRole {
    LAB("Lab"),
    TA("Teaching Assistant"),
    GRADER("Grader");

    private final String typeName;

    // Constructor corect pentru enumul ClassRole
    ClassRole(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
