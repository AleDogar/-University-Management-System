package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.service.CourseService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TeachingAssignmentValidator {

    private final CourseService courseService;

    public TeachingAssignmentValidator(CourseService courseService) {
        this.courseService = courseService;
    }

    public void validateTeachingAssignment(TeachingAssignment assignment) {
        if (assignment == null) {
            throw new RuntimeException("Asignarea nu poate fi null!");
        }

        validateId(assignment.getId());
        validateCourseId(assignment.getCourseId());
        validateStaffId(assignment.getStaffId());
        validateClassType(assignment.getClassType());
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new RuntimeException("ID-ul asignării este obligatoriu!");
        }

        if (!id.matches("TA[0-9]+")) {
            throw new RuntimeException("ID-ul asignării trebuie să înceapă cu 'TA' urmat de numere!");
        }
    }

    private void validateCourseId(String courseId) {
        if (courseId == null || courseId.trim().isEmpty()) {
            throw new RuntimeException("ID-ul cursului este obligatoriu!");
        }

        if (!courseId.matches("C[0-9]+")) {
            throw new RuntimeException("ID-ul cursului trebuie să înceapă cu 'C' urmat de numere!");
        }

        // Verifică dacă cursul există
        Map<String, ?> courses = courseService.findAll();
        if (!courses.containsKey(courseId)) {
            throw new RuntimeException("Cursul cu ID-ul '" + courseId + "' nu există!");
        }
    }

    private void validateStaffId(String staffId) {
        if (staffId == null || staffId.trim().isEmpty()) {
            throw new RuntimeException("ID-ul staff-ului este obligatoriu!");
        }

        // Format simplu: T sau A urmat de numere
        if (!staffId.matches("(T|A)[0-9]+")) {
            throw new RuntimeException("ID staff invalid! Folosește formatul: T1, T2, A1, A2, etc.");
        }
    }

    private void validateClassType(Object classType) {
        if (classType == null) {
            throw new RuntimeException("Tipul clasei este obligatoriu!");
        }

        String type = classType.toString();
        if (!type.equals("COURSE") && !type.equals("LAB") && !type.equals("SEMINARY")) {
            throw new RuntimeException("Tipul clasei trebuie să fie: COURSE, LAB sau SEMINARY!");
        }
    }
}