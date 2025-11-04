package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        if (student.getStudentID() == null || student.getStudentID().isEmpty()) {
            student.setStudentID(UUID.randomUUID().toString());
        }
        return studentRepository.save(student);
    }

    public void removeStudent(String id) {
        studentRepository.deleteById(id);
    }
}
