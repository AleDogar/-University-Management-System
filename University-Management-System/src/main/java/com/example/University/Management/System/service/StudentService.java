package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void removeStudent(String studentId) {
        Student student = studentRepository.findById(studentId);
        if (student != null) {
            studentRepository.delete(student);
        }
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
