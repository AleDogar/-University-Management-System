package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public boolean create(Course course) {
        if (repository.existsById(course.getCourseID())) {
            return false;
        }
        repository.save(course);
        return true;
    }

    public Map<String, Course> findAll() {
        List<Course> list = repository.findAll();
        Map<String, Course> map = new HashMap<>();
        for (Course c : list) {
            map.put(c.getCourseID(), c);
        }
        return map;
    }

    public Course findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, Course course) {
        if (!repository.existsById(id)) {
            return false;
        }
        course.setCourseID(id);
        repository.save(course);
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
