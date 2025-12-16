package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Creare student
    public boolean create(Student student) {
        // Trim ID și alte câmpuri pentru siguranță
        if (student.getStudentID() != null) {
            student.setStudentID(student.getStudentID().trim());
        }
        if (student.getStudentName() != null) {
            student.setStudentName(student.getStudentName().trim());
        }
        if (student.getEmail() != null) {
            student.setEmail(student.getEmail().trim());
        }

        // Debug pentru ID
        System.out.println("Creating Student with ID: " + student.getStudentID());

        // Business validation: ID unic
        if (repository.existsById(student.getStudentID())) {
            System.out.println("ID already exists: " + student.getStudentID());
            return false;
        }

        repository.save(student);
        System.out.println("Student saved: " + student);
        return true;
    }

    // Obținerea tuturor studenților
    public Map<String, Student> findAll() {
        List<Student> list = repository.findAll();
        Map<String, Student> map = new HashMap<>();
        for (Student student : list) {
            map.put(student.getStudentID(), student);
        }
        return map;
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

        // Trim câmpuri
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