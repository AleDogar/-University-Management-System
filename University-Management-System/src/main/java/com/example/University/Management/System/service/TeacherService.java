package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TeacherService {


    private TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public boolean create(Teacher teacher) {
        return repository.create(teacher);
    }

    public Map<String, Teacher> findAll() {
        return repository.findAll();
    }

    public Teacher findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, Teacher student) {
        return repository.update(id, student);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}