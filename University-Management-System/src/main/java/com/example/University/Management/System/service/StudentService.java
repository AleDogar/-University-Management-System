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

    public boolean create(Student student) {
        if (repository.existsById(student.getId())) {
            return false;
        }
        repository.save(student);
        return true;
    }

    public Map<String, Student> findAll() {
        List<Student> list = repository.findAll();
        Map<String, Student> map = new HashMap<>();
        for (Student student : list) {
            map.put(student.getId(), student);
        }
        return map;
    }

    public Student findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, Student student) {
        if (!repository.existsById(id)) {
            return false;
        }
        student.setId(id);
        repository.save(student);
        return true;
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}