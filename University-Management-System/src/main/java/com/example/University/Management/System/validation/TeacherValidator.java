package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Teacher;
import java.util.regex.Pattern;

public class TeacherValidator {

    public static void validateTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new RuntimeException("Date profesor invalide.");
        }

        if (teacher.getStaffID() == null ||
                teacher.getStaffID().trim().isEmpty() ||
                !Pattern.matches("^[A-Z][0-9]+$", teacher.getStaffID())) {
            throw new RuntimeException("ID invalid.");
        }

        if (teacher.getStaffName() == null ||
                teacher.getStaffName().trim().isEmpty() ||
                teacher.getStaffName().length() < 2) {
            throw new RuntimeException("Nume invalid.");
        }

        if (teacher.getTitle() == null ||
                teacher.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Titlu invalid.");
        }


        if (teacher.getTitle().length() < 2 || teacher.getTitle().length() > 50) {
            throw new RuntimeException("Titlu invalid (între 2 și 50 de caractere).");
        }


        if (!Pattern.matches("^[A-Za-zȘșȚțĂăÂâÎîȘșȚț\\s\\-]+$", teacher.getTitle())) {
            throw new RuntimeException("Titlu invalid (doar litere, spații, cratime).");
        }


        if (teacher.getEmail() == null ||
                teacher.getEmail().trim().isEmpty() ||
                !Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", teacher.getEmail())) {
            throw new RuntimeException("Email invalid.");
        }


        if (teacher.getDepartmentID() == null ||
                teacher.getDepartmentID().trim().isEmpty()) {
            throw new RuntimeException("Departament invalid.");
        }


        if (!Pattern.matches("^D[1-9][0-9]{0,2}$", teacher.getDepartmentID())) {
            throw new RuntimeException("ID departament invalid. Format: D1, D2, ..., D100");
        }


        String numberPart = teacher.getDepartmentID().substring(1);
        int deptNumber = Integer.parseInt(numberPart);
        if (deptNumber < 1 || deptNumber > 100) {
            throw new RuntimeException("Departament invalid. Trebuie între D1 și D100.");
        }
    }
}