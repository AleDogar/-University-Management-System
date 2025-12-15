package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.EnrollmentRepository;
import com.example.University.Management.System.repository.StudentRepository;
import com.example.University.Management.System.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository repository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Creare enrollment
    public boolean create(Enrollment enrollment) {
        // Trim ID și alte câmpuri pentru siguranță
        if (enrollment.getEnrollmentID() != null) {
            enrollment.setEnrollmentID(enrollment.getEnrollmentID().trim());
        }
        if (enrollment.getStudentID() != null) {
            enrollment.setStudentID(enrollment.getStudentID().trim());
        }
        if (enrollment.getCourseID() != null) {
            enrollment.setCourseID(enrollment.getCourseID().trim());
        }

        // Debug pentru ID
        System.out.println("Creating Enrollment with ID: " + enrollment.getEnrollmentID());

        // Business validation 1: ID unic
        if (repository.existsById(enrollment.getEnrollmentID())) {
            System.out.println("ID already exists: " + enrollment.getEnrollmentID());
            return false;
        }

        // Business validation 2: Student-ul trebuie să existe
        if (!studentRepository.existsById(enrollment.getStudentID())) {
            System.out.println("Student ID does not exist: " + enrollment.getStudentID());
            return false;
        }

        // Business validation 3: Cursul trebuie să existe
        if (!courseRepository.existsById(enrollment.getCourseID())) {
            System.out.println("Course ID does not exist: " + enrollment.getCourseID());
            return false;
        }

        repository.save(enrollment);
        System.out.println("Enrollment saved: " + enrollment);
        return true;
    }

    // Obținerea tuturor înscrierilor
    public Map<String, Enrollment> findAll() {
        List<Enrollment> list = repository.findAll();
        Map<String, Enrollment> map = new HashMap<>();
        for (Enrollment e : list) {
            map.put(e.getEnrollmentID(), e);
        }
        return map;
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

        // Trim câmpuri
        enrollment.setEnrollmentID(id.trim());
        if (enrollment.getStudentID() != null) {
            enrollment.setStudentID(enrollment.getStudentID().trim());
        }
        if (enrollment.getCourseID() != null) {
            enrollment.setCourseID(enrollment.getCourseID().trim());
        }

        // Business validation: Student-ul trebuie să existe
        if (!studentRepository.existsById(enrollment.getStudentID())) {
            System.out.println("Update failed: Student ID does not exist -> " + enrollment.getStudentID());
            return false;
        }

        // Business validation: Cursul trebuie să existe
        if (!courseRepository.existsById(enrollment.getCourseID())) {
            System.out.println("Update failed: Course ID does not exist -> " + enrollment.getCourseID());
            return false;
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

    // Verificare dacă student-ul există (pentru validare în controller)
    public boolean studentExists(String studentID) {
        if (studentID == null) return false;
        return studentRepository.existsById(studentID.trim());
    }

    // Verificare dacă cursul există (pentru validare în controller)
    public boolean courseExists(String courseID) {
        if (courseID == null) return false;
        return courseRepository.existsById(courseID.trim());
    }
}