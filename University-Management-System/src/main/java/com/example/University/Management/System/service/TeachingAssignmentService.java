package com.example.University.Management.System.service;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.repository.TeachingAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingAssignmentService {

    private final TeachingAssignmentRepository teachingAssignmentRepository;

    public TeachingAssignmentService(TeachingAssignmentRepository teachingAssignmentRepository) {
        this.teachingAssignmentRepository = teachingAssignmentRepository;
    }

    public List<TeachingAssignment> getAllTeachingAssignments() {
        return teachingAssignmentRepository.findAll();
    }

    public TeachingAssignment addTeachingAssignment(TeachingAssignment teachingAssignment) {
        return teachingAssignmentRepository.save(teachingAssignment);
    }

    public void removeTeachingAssignment(String id) {
        teachingAssignmentRepository.deleteById(id);
    }
}
