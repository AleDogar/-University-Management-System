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

    public boolean create(University university) {
        if (repository.existsById(university.getUniversityID())) {
            return false;
        }
        repository.save(university);
        return true;
    }

    public Map<String, University> findAll() {
        List<University> list = repository.findAll();
        Map<String, University> map = new HashMap<>();
        for (University university : list) {
            map.put(university.getUniversityID(), university);
        }
        return map;
    }

    public University findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, University university) {
        if (!repository.existsById(id)) {
            return false;
        }
        university.setUniversityID(id);
        repository.save(university);
        return true;
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}