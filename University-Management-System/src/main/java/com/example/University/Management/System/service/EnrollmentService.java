package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    // Creare enrollment
    public boolean create(Enrollment enrollment) {
        if (enrollment.getEnrollmentID() != null) {
            enrollment.setEnrollmentID(enrollment.getEnrollmentID().trim());
        }
        if (enrollment.getStudentID() != null) {
            enrollment.setStudentID(enrollment.getStudentID().trim());
        }
        if (enrollment.getCourseID() != null) {
            enrollment.setCourseID(enrollment.getCourseID().trim());
        }

        System.out.println("Creating Enrollment with ID: " + enrollment.getEnrollmentID());

        if (repository.existsById(enrollment.getEnrollmentID())) {
            System.out.println("ID already exists: " + enrollment.getEnrollmentID());
            return false;
        }

        repository.save(enrollment);
        System.out.println("Enrollment saved: " + enrollment);
        return true;
    }

    // Obținerea tuturor enrollment-urilor (pentru dropdowns sau alte nevoi)
    public Map<String, Enrollment> findAll() {
        List<Enrollment> list = repository.findAll();
        Map<String, Enrollment> map = new HashMap<>();
        for (Enrollment e : list) {
            map.put(e.getEnrollmentID(), e);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru enrollment-uri
    public List<Enrollment> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                     String filterEnrollmentID,
                                                     String filterStudentID,
                                                     String filterCourseID,
                                                     String filterGrade) {

        List<Enrollment> enrollments;

        // Transformăm string-urile goale în null pentru query
        String enrollmentIdParam = (filterEnrollmentID != null && !filterEnrollmentID.trim().isEmpty()) ?
                filterEnrollmentID.trim() : null;
        String studentIdParam = (filterStudentID != null && !filterStudentID.trim().isEmpty()) ?
                filterStudentID.trim() : null;
        String courseIdParam = (filterCourseID != null && !filterCourseID.trim().isEmpty()) ?
                filterCourseID.trim() : null;
        String gradeParam = (filterGrade != null && !filterGrade.trim().isEmpty()) ?
                filterGrade.trim() : null;

        // FILTRARE folosind metoda principală
        enrollments = repository.findByFilters(enrollmentIdParam, studentIdParam, courseIdParam, gradeParam);

        // SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Enrollment> comparator = getComparator(sortBy, sortDir);
            enrollments = enrollments.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return enrollments;
    }

    // Comparator pentru sortare
    private Comparator<Enrollment> getComparator(String sortBy, String sortDir) {
        Comparator<Enrollment> comparator;

        switch (sortBy) {
            case "enrollmentID":
                comparator = Comparator.comparing(Enrollment::getEnrollmentID);
                break;
            case "studentID":
                comparator = Comparator.comparing(Enrollment::getStudentID,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "courseID":
                comparator = Comparator.comparing(Enrollment::getCourseID,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "grade":
                comparator = Comparator.comparing(e -> e.getGrade() != null ?
                                e.getGrade().toString() : "",
                        String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(Enrollment::getEnrollmentID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    // Obținere enrollment după ID
    public Enrollment findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update enrollment
    public boolean update(String id, Enrollment enrollment) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Enrollment ID does not exist -> " + id);
            return false;
        }

        enrollment.setEnrollmentID(id.trim());
        if (enrollment.getStudentID() != null) {
            enrollment.setStudentID(enrollment.getStudentID().trim());
        }
        if (enrollment.getCourseID() != null) {
            enrollment.setCourseID(enrollment.getCourseID().trim());
        }

        repository.save(enrollment);
        System.out.println("Enrollment updated: " + enrollment);
        return true;
    }

    // Ștergere enrollment
    public boolean delete(String id) {
        if (id == null) return false;

        Enrollment enrollment = repository.findById(id.trim()).orElse(null);
        if (enrollment == null) {
            System.out.println("Delete failed: Enrollment ID not found -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Enrollment deleted: " + id);
        return true;
    }

    // Metode alternative folosind metoda principală
    public List<Enrollment> searchByStudentID(String studentID) {
        if (studentID == null || studentID.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return repository.findByFilters(null, studentID.trim(), null, null);
    }

    public List<Enrollment> searchByCourseID(String courseID) {
        if (courseID == null || courseID.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return repository.findByFilters(null, null, courseID.trim(), null);
    }
}