package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.model.ClassRole;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssistantService {

    private final AssistantRepository repository;

    public AssistantService(AssistantRepository repository) {
        this.repository = repository;
    }

    // Creare asistent
    public boolean create(Assistant assistant) {
        if (assistant.getStaffID() != null) {
            assistant.setStaffID(assistant.getStaffID().trim());
        }
        if (assistant.getStaffName() != null) {
            assistant.setStaffName(assistant.getStaffName().trim());
        }
        if (assistant.getEmail() != null) {
            assistant.setEmail(assistant.getEmail().trim());
        }

        System.out.println("Creating Assistant with ID: " + assistant.getStaffID());

        if (repository.existsById(assistant.getStaffID())) {
            System.out.println("ID already exists: " + assistant.getStaffID());
            return false;
        }

        repository.save(assistant);
        System.out.println("Assistant saved: " + assistant);
        return true;
    }

    // Obținerea tuturor asistenților (pentru dropdowns sau alte nevoi)
    public Map<String, Assistant> findAll() {
        List<Assistant> list = repository.findAll();
        Map<String, Assistant> map = new HashMap<>();
        for (Assistant assistant : list) {
            map.put(assistant.getStaffID(), assistant);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru asistenți
    public List<Assistant> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                    String filterStaffName, String filterRole,
                                                    String filterEmail) {

        List<Assistant> assistants;

        // 1. CONVERTIRE FILTRE
        ClassRole roleEnum = null;
        if (filterRole != null && !filterRole.trim().isEmpty()) {
            try {
                roleEnum = ClassRole.valueOf(filterRole.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Dacă valoarea nu este validă, ignorăm filtrul
                System.out.println("Invalid role filter: " + filterRole);
            }
        }

        // 2. TRANSFORMARE PARAMETRI
        String staffNameParam = (filterStaffName != null && !filterStaffName.trim().isEmpty()) ?
                filterStaffName.trim() : null;
        String emailParam = (filterEmail != null && !filterEmail.trim().isEmpty()) ?
                filterEmail.trim() : null;

        // 3. FILTRARE folosind metoda principală
        assistants = repository.findByFilters(staffNameParam, roleEnum, emailParam);

        // 4. SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Assistant> comparator = getComparator(sortBy, sortDir);
            assistants = assistants.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return assistants;
    }

    // Comparator pentru sortare
    private Comparator<Assistant> getComparator(String sortBy, String sortDir) {
        Comparator<Assistant> comparator;

        switch (sortBy) {
            case "staffID":
                comparator = Comparator.comparing(Assistant::getStaffID);
                break;
            case "staffName":
                comparator = Comparator.comparing(Assistant::getStaffName,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "role":
                comparator = Comparator.comparing(a -> a.getRole().getTypeName());
                break;
            case "email":
                comparator = Comparator.comparing(Assistant::getEmail,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(Assistant::getStaffID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    // Obținere asistent după ID
    public Assistant findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update asistent
    public boolean update(String id, Assistant assistant) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Assistant ID does not exist -> " + id);
            return false;
        }

        assistant.setStaffID(id.trim());
        if (assistant.getStaffName() != null) {
            assistant.setStaffName(assistant.getStaffName().trim());
        }
        if (assistant.getEmail() != null) {
            assistant.setEmail(assistant.getEmail().trim());
        }

        repository.save(assistant);
        System.out.println("Assistant updated: " + assistant);
        return true;
    }

    // Ștergere asistent
    public boolean delete(String id) {
        if (id == null) return false;

        Assistant assistant = repository.findById(id.trim()).orElse(null);
        if (assistant == null) {
            System.out.println("Delete failed: Assistant ID not found -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Assistant deleted: " + id);
        return true;
    }
}