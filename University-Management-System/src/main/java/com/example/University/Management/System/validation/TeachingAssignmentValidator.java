package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.model.ClassType;
import java.util.regex.Pattern;

public class TeachingAssignmentValidator {

    public void validateTeachingAssignment(TeachingAssignment assignment) {
        if (assignment == null) {
            throw new RuntimeException("Date atribuire invalide.");
        }

        validateID(assignment.getId());
        validateCourseID(assignment.getCourseId());
        validateClassType(assignment.getClassType());
    }

    private void validateID(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new RuntimeException("ID atribuire invalid.");
        }
        if (!Pattern.matches("^TA[1-9][0-9]{0,2}$", id)) {
            throw new RuntimeException("ID atribuire invalid. Format: TA1, TA2, ..., TA999");
        }
    }

    private void validateCourseID(String courseId) {
        if (courseId == null || courseId.trim().isEmpty()) {
            throw new RuntimeException("ID curs invalid.");
        }
        if (!Pattern.matches("^C[1-9][0-9]{0,2}$", courseId)) {
            throw new RuntimeException("ID curs invalid. Format: C1, C2, ..., C999");
        }
    }

    private void validateClassType(ClassType classType) {
        if (classType == null) {
            throw new RuntimeException("Tip de clasă invalid.");
        }
    }
}