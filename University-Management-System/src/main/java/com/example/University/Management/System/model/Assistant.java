package com.example.University.Management.System.model;

public class Assistant {
    private ClassRole role;

    public Assistant(ClassRole role) {
        this.role = role;
    }

    public ClassRole getRole() {
        return role;
    }

    public void setRole(ClassRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "role=" + role +
                '}';
    }
}
