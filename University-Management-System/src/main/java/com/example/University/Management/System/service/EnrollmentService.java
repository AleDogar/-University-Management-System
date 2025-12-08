package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public boolean create(Enrollment enrollment) {
        if (repository.existsById(enrollment.getEnrollmentID())) {
            return false;
        }
        repository.save(enrollment);
        return true;
    }

    public Map<String, Enrollment> findAll() {
        List<Enrollment> list = repository.findAll();
        Map<String, Enrollment> map = new HashMap<>();
        for (Enrollment e : list) {
            map.put(e.getEnrollmentID(), e);
        }
        return map;
    }

    public Enrollment findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, Enrollment enrollment) {
        if (!repository.existsById(id)) {
            return false;
        }
        enrollment.setEnrollmentID(id);
        repository.save(enrollment);
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
