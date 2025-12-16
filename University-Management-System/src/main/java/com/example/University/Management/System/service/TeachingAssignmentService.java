package com.example.University.Management.System.service;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.model.ClassType;
import com.example.University.Management.System.repository.TeachingAssignmentRepository;
import com.example.University.Management.System.repository.CourseRepository;
import com.example.University.Management.System.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        if (assignment.getAssignmentID() != null) {
            assignment.setAssignmentID(assignment.getAssignmentID().trim());
        }
        if (assignment.getStaffID() != null) {
            assignment.setStaffID(assignment.getStaffID().trim());
        }

        System.out.println("Creating TeachingAssignment with ID: " + assignment.getAssignmentID());

        if (repository.existsById(assignment.getAssignmentID())) {
            System.out.println("ID already exists: " + assignment.getAssignmentID());
            return false;
        }

        if (assignment.getCourse() == null || !courseRepository.existsById(assignment.getCourse().getCourseID())) {
            System.out.println("Course does not exist");
            return false;
        }

        if (!teacherRepository.existsById(assignment.getStaffID())) {
            System.out.println("Teacher does not exist: " + assignment.getStaffID());
            return false;
        }

        repository.save(assignment);
        System.out.println("TeachingAssignment saved: " + assignment);
        return true;
    }

    // Obținerea tuturor asignărilor (pentru alte situații)
    public Map<String, TeachingAssignment> findAll() {
        List<TeachingAssignment> list = repository.findAll();
        Map<String, TeachingAssignment> map = new HashMap<>();
        for (TeachingAssignment assignment : list) {
            map.put(assignment.getAssignmentID(), assignment);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru asignări (folosește doar findByFilters)
    public List<TeachingAssignment> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                             String filterClassType, String filterStaffID,
                                                             String filterCourseID) {

        // 1. CONVERTIRE FILTRE
        ClassType classTypeEnum = null;
        if (filterClassType != null && !filterClassType.trim().isEmpty()) {
            try {
                classTypeEnum = ClassType.valueOf(filterClassType.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Dacă valoarea nu este validă, ignorăm filtrul
                System.out.println("Invalid class type filter: " + filterClassType);
            }
        }

        // 2. FILTRARE folosind metoda principală
        // Transformăm string-urile goale în null pentru query
        String staffIDParam = (filterStaffID != null && !filterStaffID.trim().isEmpty()) ?
                filterStaffID.trim() : null;
        String courseIDParam = (filterCourseID != null && !filterCourseID.trim().isEmpty()) ?
                filterCourseID.trim() : null;

        List<TeachingAssignment> assignments = repository.findByFilters(
                classTypeEnum, staffIDParam, courseIDParam);

        // 3. SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<TeachingAssignment> comparator = getComparator(sortBy, sortDir);
            assignments = assignments.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return assignments;
    }

    // Comparator pentru sortare
    private Comparator<TeachingAssignment> getComparator(String sortBy, String sortDir) {
        Comparator<TeachingAssignment> comparator;

        switch (sortBy) {
            case "assignmentID":
                comparator = Comparator.comparing(TeachingAssignment::getAssignmentID);
                break;
            case "classType":
                comparator = Comparator.comparing(ta -> ta.getClassType().getTypeName());
                break;
            case "staffID":
                comparator = Comparator.comparing(TeachingAssignment::getStaffID,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "courseID":
                comparator = Comparator.comparing(ta ->
                                ta.getCourse() != null ? ta.getCourse().getCourseID() : "",
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "courseTitle":
                comparator = Comparator.comparing(ta ->
                                ta.getCourse() != null ? ta.getCourse().getTitle() : "",
                        String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(TeachingAssignment::getAssignmentID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
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

        assignment.setAssignmentID(id.trim());
        if (assignment.getStaffID() != null) {
            assignment.setStaffID(assignment.getStaffID().trim());
        }

        if (assignment.getCourse() == null || !courseRepository.existsById(assignment.getCourse().getCourseID())) {
            System.out.println("Update failed: Course does not exist");
            return false;
        }

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