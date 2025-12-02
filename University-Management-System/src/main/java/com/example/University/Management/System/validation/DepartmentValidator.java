package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Department;
import java.util.regex.Pattern;

public class DepartmentValidator {

    public static void validateDepartment(Department department) {
        if (department == null) {
            throw new RuntimeException("Date departament invalide.");
        }

        // Validare ID: D urmat de 1-3 cifre (D1, D10, D100)
        if (department.getDepartmentID() == null ||
                department.getDepartmentID().trim().isEmpty()) {
            throw new RuntimeException("ID departament invalid.");
        }

        // Verifică format: D urmat de cifre
        if (!Pattern.matches("^D[1-9][0-9]{0,2}$", department.getDepartmentID())) {
            throw new RuntimeException("ID departament invalid. Format: D1, D2, ..., D100");
        }

        // Verifică că numărul e între 1 și 100
        String numberPart = department.getDepartmentID().substring(1);
        int deptNumber = Integer.parseInt(numberPart);
        if (deptNumber < 1 || deptNumber > 100) {
            throw new RuntimeException("Departament invalid. Trebuie între D1 și D100.");
        }

        // Validare Nume
        if (department.getDepartmentName() == null ||
                department.getDepartmentName().trim().isEmpty() ||
                department.getDepartmentName().length() < 2 ||
                department.getDepartmentName().length() > 100) {
            throw new RuntimeException("Nume departament invalid (2-100 caractere).");
        }

        // Validare Telefon (ex: 0712345678)
        if (department.getPhoneNumber() == null ||
                department.getPhoneNumber().trim().isEmpty() ||
                !Pattern.matches("^07[0-9]{8}$", department.getPhoneNumber())) {
            throw new RuntimeException("Număr telefon invalid. Format: 07xxxxxxxx");
        }
    }
}