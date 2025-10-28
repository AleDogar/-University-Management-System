package com.example.University.Management.System.model;

public class Assistant {
    private String assistantID;
    private String assistName;
    private String email;
    private String department;
    private String phoneNumber;

    public Assistant(String assistantID, String name, String email, String department, String phoneNumber) {
        this.assistantID = assistantID;
        this.assistName = assistName;
        this.email = email;
        this.department = department;
        this.phoneNumber = phoneNumber;
    }

    public String getAssistantID() {
        return assistantID;
    }

    public void setAssistantID(String assistantID) {
        this.assistantID = assistantID;
    }

    public String getName() {
        return assistName;
    }

    public void setName(String name) {
        this.assistName = assistName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "assistantID='" + assistantID + '\'' +
                ", name='" + assistName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
            '}';
}
}