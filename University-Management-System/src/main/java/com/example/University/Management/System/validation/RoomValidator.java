package com.example.University.Management.System.validation;

import com.example.University.Management.System.model.Room;
import java.util.regex.Pattern;

public class RoomValidator {

    public static void validateRoom(Room room) {
        if (room == null) {
            throw new RuntimeException("Date sală invalide.");
        }

        if (room.getRoomID() == null ||
                room.getRoomID().trim().isEmpty()) {
            throw new RuntimeException("ID sală invalid.");
        }

        if (!Pattern.matches("^R[1-9][0-9]{0,2}$", room.getRoomID())) {
            throw new RuntimeException("ID sală invalid. Format: R1, R2, ..., R100");
        }

        String numberPart = room.getRoomID().substring(1);
        int roomNumber = Integer.parseInt(numberPart);
        if (roomNumber < 1 || roomNumber > 100) {
            throw new RuntimeException("Sală invalidă. Trebuie între R1 și R100.");
        }

        if (room.getNumber() == null ||
                room.getNumber().trim().isEmpty() ||
                room.getNumber().length() < 1 ||
                room.getNumber().length() > 20) {
            throw new RuntimeException("Număr sală invalid (1-20 caractere).");
        }

        if (!Pattern.matches("^[A-Za-z0-9\\s\\-]+$", room.getNumber())) {
            throw new RuntimeException("Număr sală invalid. Poate conține litere, cifre, spații, cratime.");
        }

        if (room.getCapacity() < 1 || room.getCapacity() > 500) {
            throw new RuntimeException("Capacitate invalidă (1-500 persoane).");
        }
    }
}