package com.example.University.Management.System.service;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UniversityService {

    private final UniversityRepository repository;

    public UniversityService(UniversityRepository repository) {
        this.repository = repository;
    }

    // Creare universitate
    public boolean create(University university) {
        // Trim ID și alte câmpuri pentru siguranță
        if (university.getUniversityID() != null) {
            university.setUniversityID(university.getUniversityID().trim());
        }
        if (university.getUniversityName() != null) {
            university.setUniversityName(university.getUniversityName().trim());
        }
        if (university.getCity() != null) {
            university.setCity(university.getCity().trim());
        }

        // Debug pentru ID
        System.out.println("Creating University with ID: " + university.getUniversityID());

        // Business validation: ID unic
        if (repository.existsById(university.getUniversityID())) {
            System.out.println("ID already exists: " + university.getUniversityID());
            return false; // ID-ul există deja
        }

        repository.save(university);
        System.out.println("University saved: " + university);
        return true;
    }

    // Obținerea tuturor universităților
    public Map<String, University> findAll() {
        List<University> list = repository.findAll();
        Map<String, University> map = new HashMap<>();
        for (University u : list) {
            map.put(u.getUniversityID(), u);
        }
        return map;
    }

    // Obținere universitate după ID
    public University findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update universitate
    public boolean update(String id, University university) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: University ID does not exist -> " + id);
            return false; // Universitatea nu există
        }

        // Trim ID și alte câmpuri
        university.setUniversityID(id.trim());
        if (university.getUniversityName() != null) {
            university.setUniversityName(university.getUniversityName().trim());
        }
        if (university.getCity() != null) {
            university.setCity(university.getCity().trim());
        }

        repository.save(university);
        System.out.println("University updated: " + university);
        return true;
    }

    // Ștergere universitate
    public boolean delete(String id) {
        if (id == null) return false;

        University uni = repository.findById(id.trim()).orElse(null);
        if (uni == null) {
            System.out.println("Delete failed: University ID not found -> " + id);
            return false; // Universitatea nu există
        }

        // Business validation: nu putem șterge universități care au departamente sau săli
        if ((uni.getDepartments() != null && !uni.getDepartments().isEmpty()) ||
                (uni.getRooms() != null && !uni.getRooms().isEmpty())) {
            System.out.println("Delete failed: University has related departments or rooms -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("University deleted: " + id);
        return true;
    }
}
