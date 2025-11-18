package com.example.University.Management.System.service;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.repository.TeachingAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeachingAssignmentService {

    private final TeachingAssignmentRepository repository;

    public TeachingAssignmentService(TeachingAssignmentRepository repository) {
        this.repository = repository;
    }

    public List<TeachingAssignment> getAllTeachingAssignments() {
        return repository.findAll();
    }

    public TeachingAssignment getTeachingAssignmentById(String id) {
        return repository.findById(id);
    }

    public TeachingAssignment saveTeachingAssignment(TeachingAssignment ta) {
        if (ta.getId() == null || ta.getId().isEmpty()) {
            ta.setId(UUID.randomUUID().toString());
        }
        repository.save(ta);
        return ta;
    }

    public void deleteTeachingAssignment(String id) {
        repository.deleteById(id);
    }
}
