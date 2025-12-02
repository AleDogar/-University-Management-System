package com.example.University.Management.System.service;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UniversityService {


    private UniversityRepository repository;

    @Autowired
    public UniversityService(UniversityRepository repository) {
        this.repository = repository;
    }

    public boolean create(University assignment) {
        return repository.create(assignment);
    }

    public Map<String, University> findAll() {
        return repository.findAll();
    }

    public University findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, University university) {
        return repository.update(id, university);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}