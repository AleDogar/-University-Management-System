package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssistantService {

    private final AssistantRepository repository;

    public AssistantService(AssistantRepository repository) {
        this.repository = repository;
    }

    // Creare asistent
    public boolean create(Assistant assistant) {
        // Trim ID și alte câmpuri pentru siguranță
        if (assistant.getStaffID() != null) {
            assistant.setStaffID(assistant.getStaffID().trim());
        }
        if (assistant.getStaffName() != null) {
            assistant.setStaffName(assistant.getStaffName().trim());
        }
        if (assistant.getEmail() != null) {
            assistant.setEmail(assistant.getEmail().trim());
        }

        // Debug pentru ID
        System.out.println("Creating Assistant with ID: " + assistant.getStaffID());

        // Business validation: ID unic
        if (repository.existsById(assistant.getStaffID())) {
            System.out.println("ID already exists: " + assistant.getStaffID());
            return false;
        }

        repository.save(assistant);
        System.out.println("Assistant saved: " + assistant);
        return true;
    }

    // Obținerea tuturor asistenților
    public Map<String, Assistant> findAll() {
        List<Assistant> list = repository.findAll();
        Map<String, Assistant> map = new HashMap<>();
        for (Assistant assistant : list) {
            map.put(assistant.getStaffID(), assistant);
        }
        return map;
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

        // Trim câmpuri
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