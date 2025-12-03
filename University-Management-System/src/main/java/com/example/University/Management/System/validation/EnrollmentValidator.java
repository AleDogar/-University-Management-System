package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.model.ClassGrade;
import java.util.regex.Pattern;

public class EnrollmentValidator {

    public static void validateEnrollment(Enrollment enrollment) {
        if (enrollment == null) {
            throw new RuntimeException("Date înscriere invalide.");
        }

        // Validare ID (ex: E1, E10, E100)
        if (enrollment.getEnrollmentID() == null ||
                enrollment.getEnrollmentID().trim().isEmpty()) {
            throw new RuntimeException("ID înscriere invalid.");
        }

        // Verifică format: E urmat de cifre
        if (!Pattern.matches("^E[1-9][0-9]{0,2}$", enrollment.getEnrollmentID())) {
            throw new RuntimeException("ID înscriere invalid. Format: E1, E2, ..., E100");
        }

        // Validare Course ID (ex: C1, C10, C100)
        if (enrollment.getCourseID() == null ||
                enrollment.getCourseID().trim().isEmpty()) {
            throw new RuntimeException("ID curs invalid.");
        }

        // Verifică format: C urmat de cifre
        if (!Pattern.matches("^C[1-9][0-9]{0,2}$", enrollment.getCourseID())) {
            throw new RuntimeException("ID curs invalid. Format: C1, C2, ..., C100");
        }

        // Validare Grade
        if (enrollment.getGrade() == null) {
            throw new RuntimeException("Notă invalidă.");
        }

        // Verifică că grade este unul dintre valorile enum ClassGrade
        try {
            ClassGrade.valueOf(enrollment.getGrade().name());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Notă invalidă. Valori permise: A, B, C, D, F");
        }
    }
}