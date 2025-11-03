package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repo;
    public CourseService(CourseRepository repo) { this.repo = repo; }
    public List<Course> getAllCourses() { return repo.findAll(); }
    public Course addCourse(Course c) { return repo.save(c); }
    public void removeCourse(String id) { repo.deleteById(id); }
}
