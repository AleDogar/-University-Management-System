package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.service.DepartmentService;
import com.example.University.Management.System.service.RoomService;
import java.util.regex.Pattern;

public class CourseValidator {

    private final DepartmentService departmentService;
    private final RoomService roomService;

    public CourseValidator(DepartmentService departmentService, RoomService roomService) {
        this.departmentService = departmentService;
        this.roomService = roomService;
    }

    public void validateCourse(Course course) {
        if (course == null) {
            throw new RuntimeException("Date curs invalide.");
        }

        // Validare ID Curs
        validateCourseID(course.getCourseID());

        // Validare Titlu
        validateTitle(course.getTitle());

        // Validare Credite
        validateCredits(course.getCredits());

        // Validare Departament ID
        validateDepartmentID(course.getDepartmentID());

        // Validare Sala ID
        validateRoomID(course.getRoomID());
    }

    private void validateCourseID(String courseID) {
        if (courseID == null || courseID.trim().isEmpty()) {
            throw new RuntimeException("ID curs invalid.");
        }

        if (courseID.length() < 2) {
            throw new RuntimeException("ID curs invalid.");
        }

        if (!Pattern.matches("^C[0-9]+$", courseID)) {
            throw new RuntimeException("ID curs invalid. Format: C + număr (ex: C1, C123)");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new RuntimeException("Titlu invalid.");
        }

        if (title.length() < 2 || title.length() > 200) {
            throw new RuntimeException("Titlu invalid. Minim 2 caractere, maxim 200.");
        }
    }

    private void validateCredits(int credits) {
        if (credits < 1 || credits > 30) {
            throw new RuntimeException("Credite invalide. Trebuie să fie între 1 și 30.");
        }
    }

    private void validateDepartmentID(String departmentID) {
        if (departmentID == null || departmentID.trim().isEmpty()) {
            throw new RuntimeException("ID departament invalid.");
        }

        // Verifică formatul
        if (!Pattern.matches("^D[0-9]+$", departmentID)) {
            throw new RuntimeException("ID departament invalid. Format: D + număr (ex: D1, D10)");
        }

        // Verifică existența departamentului
        if (departmentService != null && departmentService.findById(departmentID) == null) {
            throw new RuntimeException("Departamentul cu ID-ul '" + departmentID + "' nu există.");
        }
    }

    private void validateRoomID(String roomID) {
        if (roomID == null || roomID.trim().isEmpty()) {
            throw new RuntimeException("ID sală invalid.");
        }

        // Verifică formatul
        if (!Pattern.matches("^R[0-9]+$", roomID)) {
            throw new RuntimeException("ID sală invalid. Format: R + număr (ex: R101, R205)");
        }

        // Verifică existența sălii
        if (roomService != null && roomService.findById(roomID) == null) {
            throw new RuntimeException("Sala cu ID-ul '" + roomID + "' nu există.");
        }
    }
}