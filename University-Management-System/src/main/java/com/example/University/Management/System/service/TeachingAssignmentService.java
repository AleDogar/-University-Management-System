package com.example.University.Management.System.service;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.repository.TeachingAssignmentRepository;
import com.example.University.Management.System.repository.CourseRepository;
import com.example.University.Management.System.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeachingAssignmentService {

    private final TeachingAssignmentRepository repository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public TeachingAssignmentService(TeachingAssignmentRepository repository,
                                     CourseRepository courseRepository,
                                     TeacherRepository teacherRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    // Creare asignare
    public boolean create(TeachingAssignment assignment) {
        // Trim ID și alte câmpuri pentru siguranță
        if (assignment.getAssignmentID() != null) {
            assignment.setAssignmentID(assignment.getAssignmentID().trim());
        }
        if (assignment.getStaffID() != null) {
            assignment.setStaffID(assignment.getStaffID().trim());
        }

        // Debug pentru ID
        System.out.println("Creating TeachingAssignment with ID: " + assignment.getAssignmentID());

        // Business validation: ID unic
        if (repository.existsById(assignment.getAssignmentID())) {
            System.out.println("ID already exists: " + assignment.getAssignmentID());
            return false;
        }

        // Business validation: Cursul trebuie să existe
        if (assignment.getCourse() == null || !courseRepository.existsById(assignment.getCourse().getCourseID())) {
            System.out.println("Course does not exist");
            return false;
        }

        // Business validation: Profesorul trebuie să existe
        if (!teacherRepository.existsById(assignment.getStaffID())) {
            System.out.println("Teacher does not exist: " + assignment.getStaffID());
            return false;
        }

        repository.save(assignment);
        System.out.println("TeachingAssignment saved: " + assignment);
        return true;
    }

    // Obținerea tuturor asignărilor
    public Map<String, TeachingAssignment> findAll() {
        List<TeachingAssignment> list = repository.findAll();
        Map<String, TeachingAssignment> map = new HashMap<>();
        for (TeachingAssignment assignment : list) {
            map.put(assignment.getAssignmentID(), assignment);
        }
        return map;
    }

    // Obținere asignare după ID
    public TeachingAssignment findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update asignare
    public boolean update(String id, TeachingAssignment assignment) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Assignment ID does not exist -> " + id);
            return false;
        }

        // Trim câmpuri
        assignment.setAssignmentID(id.trim());
        if (assignment.getStaffID() != null) {
            assignment.setStaffID(assignment.getStaffID().trim());
        }

        // Business validation: Cursul trebuie să existe
        if (assignment.getCourse() == null || !courseRepository.existsById(assignment.getCourse().getCourseID())) {
            System.out.println("Update failed: Course does not exist");
            return false;
        }

        // Business validation: Profesorul trebuie să existe
        if (!teacherRepository.existsById(assignment.getStaffID())) {
            System.out.println("Update failed: Teacher does not exist -> " + assignment.getStaffID());
            return false;
        }

        repository.save(assignment);
        System.out.println("TeachingAssignment updated: " + assignment);
        return true;
    }

    // Ștergere asignare
    public boolean delete(String id) {
        if (id == null) return false;

        TeachingAssignment assignment = repository.findById(id.trim()).orElse(null);
        if (assignment == null) {
            System.out.println("Delete failed: Assignment ID not found -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("TeachingAssignment deleted: " + id);
        return true;
    }
}