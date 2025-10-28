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

    public void addTeachingAssignment(TeachingAssignment teachingAssignment) {
        teachingAssignmentRepository.save(teachingAssignment);
    }

    public void removeTeachingAssignment(String assignmentId) {
        TeachingAssignment teachingAssignment = teachingAssignmentRepository.findById(assignmentId);
        if (teachingAssignment != null) {
            teachingAssignmentRepository.delete(teachingAssignment);
        }
    }

    public TeachingAssignment getTeachingAssignmentById(String id) {
        return teachingAssignmentRepository.findById(id);
    }

    public List<TeachingAssignment> getAllTeachingAssignments() {
        return teachingAssignmentRepository.findAll();
    }
}
