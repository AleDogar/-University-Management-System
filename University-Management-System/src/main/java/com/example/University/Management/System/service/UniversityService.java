
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
        // Business validation: ID unic
        if (repository.existsById(university.getUniversityID())) {
            return false; // ID-ul există deja
        }

        // Field validation poate fi făcută în controller cu @Valid
        repository.save(university);
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
        return repository.findById(id).orElse(null);
    }

    // Update universitate
    public boolean update(String id, University university) {
        if (!repository.existsById(id)) {
            return false; // Universitatea nu există
        }

        // Păstrăm ID-ul original
        university.setUniversityID(id);
        repository.save(university);
        return true;
    }

    // Ștergere universitate
    public boolean delete(String id) {
        University uni = repository.findById(id).orElse(null);
        if (uni == null) {
            return false; // Universitatea nu există
        }

        // Business validation: nu putem șterge universități care au departamente sau săli
        if ((uni.getDepartments() != null && !uni.getDepartments().isEmpty()) ||
                (uni.getRooms() != null && !uni.getRooms().isEmpty())) {
            return false; // Relații existente, nu ștergem
        }

        repository.deleteById(id);
        return true;
    }
}
