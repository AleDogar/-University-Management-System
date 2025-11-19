package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course getCourseById(String id) {
        return repository.findById(id);
    }

    public Course saveCourse(Course course) {
        if (course.getCourseID() == null || course.getCourseID().isEmpty()) {
            course.setCourseID(UUID.randomUUID().toString());
        }
        repository.save(course);
        return course;
    }

    public void deleteCourse(String id) {
        repository.deleteById(id);
    }
}
