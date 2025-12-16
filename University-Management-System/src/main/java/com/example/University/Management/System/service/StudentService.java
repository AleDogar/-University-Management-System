package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Creare student
    public boolean create(Student student) {
        if (student.getStudentID() != null) {
            student.setStudentID(student.getStudentID().trim());
        }
        if (student.getStudentName() != null) {
            student.setStudentName(student.getStudentName().trim());
        }
        if (student.getEmail() != null) {
            student.setEmail(student.getEmail().trim());
        }

        System.out.println("Creating Student with ID: " + student.getStudentID());

        if (repository.existsById(student.getStudentID())) {
            System.out.println("ID already exists: " + student.getStudentID());
            return false;
        }

        repository.save(student);
        System.out.println("Student saved: " + student);
        return true;
    }

    // Obținerea tuturor studenților (pentru dropdowns sau alte nevoi)
    public Map<String, Student> findAll() {
        List<Student> list = repository.findAll();
        Map<String, Student> map = new HashMap<>();
        for (Student student : list) {
            map.put(student.getStudentID(), student);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru studenți
    public List<Student> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                  String filterStudentName, String filterEmail) {

        List<Student> students;

        // Transformăm string-urile goale în null pentru query
        String nameParam = (filterStudentName != null && !filterStudentName.trim().isEmpty()) ?
                filterStudentName.trim() : null;
        String emailParam = (filterEmail != null && !filterEmail.trim().isEmpty()) ?
                filterEmail.trim() : null;

        // FILTRARE folosind metoda principală
        students = repository.findByFilters(nameParam, emailParam);

        // SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Student> comparator = getComparator(sortBy, sortDir);
            students = students.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return students;
    }

    // Comparator pentru sortare
    private Comparator<Student> getComparator(String sortBy, String sortDir) {
        Comparator<Student> comparator;

        switch (sortBy) {
            case "studentID":
                comparator = Comparator.comparing(Student::getStudentID);
                break;
            case "studentName":
                comparator = Comparator.comparing(Student::getStudentName,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "email":
                comparator = Comparator.comparing(Student::getEmail,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(Student::getStudentID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    // Obținere student după ID
    public Student findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update student
    public boolean update(String id, Student student) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Student ID does not exist -> " + id);
            return false;
        }

        student.setStudentID(id.trim());
        if (student.getStudentName() != null) {
            student.setStudentName(student.getStudentName().trim());
        }
        if (student.getEmail() != null) {
            student.setEmail(student.getEmail().trim());
        }

        repository.save(student);
        System.out.println("Student updated: " + student);
        return true;
    }

    // Ștergere student
    public boolean delete(String id) {
        if (id == null) return false;

        Student student = repository.findById(id.trim()).orElse(null);
        if (student == null) {
            System.out.println("Delete failed: Student ID not found -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Student deleted: " + id);
        return true;
    }
}