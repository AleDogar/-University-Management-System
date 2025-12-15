package com.example.University.Management.System.service;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.repository.TeachingAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeachingAssignmentService {

    private final TeachingAssignmentRepository repository;

    public TeachingAssignmentService(TeachingAssignmentRepository repository) {
        this.repository = repository;
    }

    public boolean create(TeachingAssignment teachingAssignment) {
        if (repository.existsById(teachingAssignment.getId())) {
            return false;
        }
        repository.save(teachingAssignment);
        return true;
    }

    public Map<String, TeachingAssignment> findAll() {
        List<TeachingAssignment> list = repository.findAll();
        Map<String, TeachingAssignment> map = new HashMap<>();
        for (TeachingAssignment teachingAssignment : list) {
            map.put(teachingAssignment.getId(), teachingAssignment);
        }
        return map;
    }

    public TeachingAssignment findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, TeachingAssignment teachingAssignment) {
        if (!repository.existsById(id)) {
            return false;
        }
        teachingAssignment.setId(id);
        repository.save(teachingAssignment);
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