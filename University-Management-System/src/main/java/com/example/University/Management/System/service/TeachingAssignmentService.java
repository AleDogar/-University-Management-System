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

    public boolean create(TeachingAssignment assignment) {
        if (repository.existsById(assignment.getId())) {
            return false;
        }
        repository.save(assignment);
        return true;
    }

    public Map<String, TeachingAssignment> findAll() {
        List<TeachingAssignment> list = repository.findAll();
        Map<String, TeachingAssignment> map = new HashMap<>();
        for (TeachingAssignment t : list) {
            map.put(t.getId(), t);
        }
        return map;
    }

    public TeachingAssignment findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, TeachingAssignment assignment) {
        if (!repository.existsById(id)) {
            return false;
        }
        assignment.setId(id);
        repository.save(assignment);
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
