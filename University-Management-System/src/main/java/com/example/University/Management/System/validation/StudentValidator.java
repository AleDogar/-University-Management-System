package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Student;
import java.util.regex.Pattern;

public class StudentValidator {

    public static void validateStudent(Student student) {

        if (student == null) {
            throw new RuntimeException("Date student invalide.");
        }

        if (student.getId() == null || student.getId().trim().isEmpty()) {
            throw new RuntimeException("ID invalid.");
        }

        if (student.getId().length() < 2) {
            throw new RuntimeException("ID invalid.");
        }

        if (!Pattern.matches("^[A-Z][0-9]+$", student.getId())) {
            throw new RuntimeException("ID invalid.");
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new RuntimeException("Nume invalid.");
        }

        if (student.getName().length() < 2 || student.getName().length() > 100) {
            throw new RuntimeException("Nume invalid.");
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email invalid.");
        }

        if (!student.getEmail().contains("@") || !student.getEmail().contains(".")) {
            throw new RuntimeException("Email invalid.");
        }

        int atIndex = student.getEmail().indexOf('@');
        int dotIndex = student.getEmail().lastIndexOf('.');
        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex >= student.getEmail().length() - 1) {
            throw new RuntimeException("Email invalid.");
        }
    }
}