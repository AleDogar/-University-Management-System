package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.model.ClassType;
import java.util.regex.Pattern;

public class TeachingAssignmentValidator {

    public void validateTeachingAssignment(TeachingAssignment assignment) {
        if (assignment == null) {
            throw new RuntimeException("Date asignare invalide.");
        }

        // 1. Validare ID
        validateID(assignment.getId());

        // 2. Validare Course ID
        validateCourseIDFormat(assignment.getCourseId());

        // 3. Validare Staff ID
        validateStaffIDFormat(assignment.getStaffId());

        // 4. Validare Class Type
        validateClassType(assignment.getClassType());
    }

    private void validateID(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new RuntimeException("ID asignare invalid.");
        }

        if (id.length() < 2) {
            throw new RuntimeException("ID asignare invalid.");
        }

        if (!Pattern.matches("^TA[0-9]+$", id)) {
            throw new RuntimeException("ID asignare invalid. Format: TA + număr (ex: TA1, TA123)");
        }
    }

    private void validateCourseIDFormat(String courseID) {
        if (courseID == null || courseID.trim().isEmpty()) {
            throw new RuntimeException("ID curs invalid.");
        }

        if (!Pattern.matches("^C[0-9]+$", courseID)) {
            throw new RuntimeException("ID curs invalid. Format: C + număr (ex: C1, C101)");
        }
    }

    private void validateStaffIDFormat(String staffID) {
        if (staffID == null || staffID.trim().isEmpty()) {
            throw new RuntimeException("ID profesor invalid.");
        }

        if (!Pattern.matches("^T[0-9]+$", staffID)) {
            throw new RuntimeException("ID profesor invalid. Format: T + număr (ex: T1, T123)");
        }
    }

    private void validateClassType(ClassType classType) {
        if (classType == null) {
            throw new RuntimeException("Tipul clasei invalid. Selectează Lab, Seminary sau Course.");
        }
    }
}