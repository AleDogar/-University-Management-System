package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.University;
import java.util.regex.Pattern;

public class UniversityValidator {

    public void validateUniversity(University university) {
        if (university == null) {
            throw new RuntimeException("Date universitate invalide.");
        }

        // Validare ID Universitate
        validateUniversityID(university.getUniversityID());

        // Validare Nume Universitate
        validateUniversityName(university.getUniversityName());

        // Validare Oraș
        validateCity(university.getCity());
    }

    private void validateUniversityID(String universityID) {
        if (universityID == null || universityID.trim().isEmpty()) {
            throw new RuntimeException("ID universitate invalid.");
        }

        if (universityID.length() < 2) {
            throw new RuntimeException("ID universitate invalid.");
        }

        // Format: U + număr (ex: U1, U123)
        if (!Pattern.matches("^U[0-9]+$", universityID)) {
            throw new RuntimeException("ID universitate invalid. Format: U + număr (ex: U1, U123)");
        }
    }

    private void validateUniversityName(String universityName) {
        if (universityName == null || universityName.trim().isEmpty()) {
            throw new RuntimeException("Nume universitate invalid.");
        }

        if (universityName.length() < 2 || universityName.length() > 200) {
            throw new RuntimeException("Nume universitate invalid. Minim 2 caractere, maxim 200.");
        }

        // Numele trebuie să conțină doar litere, spații și caractere speciale permise
        if (!Pattern.matches("^[a-zA-ZăâîșțĂÂÎȘȚ\\s\\-'.]+$", universityName)) {
            throw new RuntimeException("Nume universitate invalid. Poate conține doar litere, spații și caractere speciale.");
        }
    }

    private void validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new RuntimeException("Oraș invalid.");
        }

        if (city.length() < 2 || city.length() > 100) {
            throw new RuntimeException("Oraș invalid. Minim 2 caractere, maxim 100.");
        }

        // Orașul trebuie să conțină doar litere și spații
        if (!Pattern.matches("^[a-zA-ZăâîșțĂÂÎȘȚ\\s\\-]+$", city)) {
            throw new RuntimeException("Oraș invalid. Poate conține doar litere și spații.");
        }
    }
}