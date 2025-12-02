package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.model.ClassRole;
import java.util.regex.Pattern;

public class AssistantValidator {

    public static void validateAssistant(Assistant assistant) {
        if (assistant == null) {
            throw new RuntimeException("Date asistent invalide.");
        }

        // Validare ID
        if (assistant.getStaffID() == null ||
                assistant.getStaffID().trim().isEmpty() ||
                !Pattern.matches("^[A-Z][0-9]+$", assistant.getStaffID())) {
            throw new RuntimeException("ID invalid.");
        }

        // Validare Nume
        if (assistant.getStaffName() == null ||
                assistant.getStaffName().trim().isEmpty() ||
                assistant.getStaffName().length() < 2) {
            throw new RuntimeException("Nume invalid.");
        }

        // Validare Rol
        if (assistant.getRole() == null) {
            throw new RuntimeException("Rol invalid.");
        }

        // Verifică că rolul este unul dintre valorile enum ClassRole
        try {
            ClassRole.valueOf(assistant.getRole().name()); // Verifică dacă e valid
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol invalid. Valori permise: LAB, TA");
        }

        // Validare Email
        if (assistant.getEmail() == null ||
                assistant.getEmail().trim().isEmpty() ||
                !Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", assistant.getEmail())) {
            throw new RuntimeException("Email invalid.");
        }
    }
}