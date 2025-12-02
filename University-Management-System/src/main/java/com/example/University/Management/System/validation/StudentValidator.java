package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Student;
import java.util.regex.Pattern;

public class StudentValidator {

    public static void validateStudent(Student student) {
        // Verifică dacă studentul este null
        if (student == null) {
            throw new RuntimeException("Date student invalide.");
        }

        // 1. Validare ID
        if (student.getId() == null || student.getId().trim().isEmpty()) {
            throw new RuntimeException("ID invalid.");
        }

        // Verifică lungimea ID (minim 2 caractere: 1 literă + 1 cifră)
        if (student.getId().length() < 2) {
            throw new RuntimeException("ID invalid.");
        }

        // Verifică formatul: 1 literă mare + cifre
        if (!Pattern.matches("^[A-Z][0-9]+$", student.getId())) {
            throw new RuntimeException("ID invalid.");
        }

        // 2. Validare Nume
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new RuntimeException("Nume invalid.");
        }

        // Verifică lungimea numelui
        if (student.getName().length() < 2 || student.getName().length() > 100) {
            throw new RuntimeException("Nume invalid.");
        }

        // 3. Validare Email
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email invalid.");
        }

        // Verifică formatul emailului (simplu)
        if (!student.getEmail().contains("@") || !student.getEmail().contains(".")) {
            throw new RuntimeException("Email invalid.");
        }

        // Verifică că @ este înainte de .
        int atIndex = student.getEmail().indexOf('@');
        int dotIndex = student.getEmail().lastIndexOf('.');
        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex >= student.getEmail().length() - 1) {
            throw new RuntimeException("Email invalid.");
        }
    }
}