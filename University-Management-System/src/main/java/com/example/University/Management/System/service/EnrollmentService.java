package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public List<Enrollment> getAllEnrollments() {
        return repository.findAll();
    }

    public Enrollment getEnrollmentById(String id) {
        return repository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment e) {
        if (e.getEnrollmentID() == null || e.getEnrollmentID().isEmpty()) {
            e.setEnrollmentID(UUID.randomUUID().toString());
        }
        repository.save(e);
        return e;
    }

    public void removeEnrollment(String id) {
        repository.deleteById(id);
    }
}
