package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.model.ClassGrade;
import java.util.regex.Pattern;

public class EnrollmentValidator {

    public static void validateEnrollment(Enrollment enrollment) {
        if (enrollment == null) {
            throw new RuntimeException("Date înscriere invalide.");
        }

        // Validare ID Enrollment
        validateEnrollmentID(enrollment.getEnrollmentID());

        // Validare Course ID
        validateCourseID(enrollment.getCourseID());

        // Validare Grade
        validateGrade(enrollment.getGrade());
    }

    private static void validateEnrollmentID(String enrollmentID) {
        if (enrollmentID == null || enrollmentID.trim().isEmpty()) {
            throw new RuntimeException("ID înscriere invalid.");
        }

        if (!Pattern.matches("^E[1-9][0-9]{0,2}$", enrollmentID)) {
            throw new RuntimeException("ID înscriere invalid. Format: E1, E2, ..., E999");
        }
    }

    private static void validateCourseID(String courseID) {
        if (courseID == null || courseID.trim().isEmpty()) {
            throw new RuntimeException("ID curs invalid.");
        }

        if (!Pattern.matches("^C[1-9][0-9]{0,2}$", courseID)) {
            throw new RuntimeException("ID curs invalid. Format: C1, C2, ..., C999");
        }
    }

    private static void validateGrade(ClassGrade grade) {
        if (grade == null) {
            throw new RuntimeException("Notă invalidă.");
        }

        // Verifică că grade nu este null și este o valoare validă din enum
        try {
            ClassGrade.valueOf(grade.name());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new RuntimeException("Notă invalidă. Valori permise: A, B, C, D, F, NA");
        }
    }
}