package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo) { this.repo = repo; }
    public List<Student> getAllStudents() { return repo.findAll(); }
    public Student addStudent(Student s) { return repo.save(s); }
    public void removeStudent(String id) { repo.deleteById(id); }
}
