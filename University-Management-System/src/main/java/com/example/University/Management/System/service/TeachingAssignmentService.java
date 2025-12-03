package com.example.University.Management.System.service;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.repository.TeachingAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TeachingAssignmentService {


    private TeachingAssignmentRepository repository;

    @Autowired
    public TeachingAssignmentService(TeachingAssignmentRepository repository) {
        this.repository = repository;
    }

    public boolean create(TeachingAssignment assignment) {
        return repository.create(assignment);
    }

    public Map<String, TeachingAssignment> findAll() {
        return repository.findAll();
    }

    public TeachingAssignment findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, TeachingAssignment assigment) {
        return repository.update(id, assigment);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}