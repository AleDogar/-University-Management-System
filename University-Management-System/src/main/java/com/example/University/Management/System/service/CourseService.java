package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CourseService {


    private CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public boolean create(Course assignment) {
        return repository.create(assignment);
    }

    public Map<String, Course> findAll() {
        return repository.findAll();
    }

    public Course findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, Course student) {
        return repository.update(id, student);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}