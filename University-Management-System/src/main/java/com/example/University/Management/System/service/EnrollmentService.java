package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EnrollmentService {


    private EnrollmentRepository repository;

    @Autowired
    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public boolean create(Enrollment assignment) {
        return repository.create(assignment);
    }

    public Map<String, Enrollment> findAll() {
        return repository.findAll();
    }

    public Enrollment findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, Enrollment student) {
        return repository.update(id, student);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}