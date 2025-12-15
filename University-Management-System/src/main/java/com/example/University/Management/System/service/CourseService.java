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
    private final DepartmentService departmentService;
    private final RoomService roomService;

    public CourseService(CourseRepository repository,
                         DepartmentService departmentService,
                         RoomService roomService) {
        this.repository = repository;
        this.departmentService = departmentService;
        this.roomService = roomService;
    }

    // Create with business validations. Throws IllegalArgumentException with a user-friendly message on failure.
    public boolean create(Course course) {
        // Trim inputs
        if (course.getCourseID() != null) course.setCourseID(course.getCourseID().trim());
        if (course.getTitle() != null) course.setTitle(course.getTitle().trim());
        if (course.getDepartmentID() != null) course.setDepartmentID(course.getDepartmentID().trim());
        if (course.getRoomID() != null) course.setRoomID(course.getRoomID().trim());

        // Business: ID required
        if (course.getCourseID() == null || course.getCourseID().isEmpty()) {
            throw new IllegalArgumentException("ID cursului este obligatoriu.");
        }

        // Business: unique ID
        if (repository.existsById(course.getCourseID())) {
            throw new IllegalArgumentException("Un curs cu acest ID există deja.");
        }

        // Business: department must exist
        if (course.getDepartmentID() == null || departmentService.findById(course.getDepartmentID()) == null) {
            throw new IllegalArgumentException("Departamentul selectat nu există.");
        }

        // Business: room must exist
        if (course.getRoomID() == null || roomService.findById(course.getRoomID()) == null) {
            throw new IllegalArgumentException("Sala selectată nu există.");
        }

        repository.save(course);
        return true;
    }

    // Update with validations
    public boolean update(String id, Course course) {
        if (id == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Cursul nu există.");
        }

        // Trim and set id to avoid changing
        if (course.getTitle() != null) course.setTitle(course.getTitle().trim());
        if (course.getDepartmentID() != null) course.setDepartmentID(course.getDepartmentID().trim());
        if (course.getRoomID() != null) course.setRoomID(course.getRoomID().trim());
        course.setCourseID(id);

        // Check department and room
        if (course.getDepartmentID() == null || departmentService.findById(course.getDepartmentID()) == null) {
            throw new IllegalArgumentException("Departamentul selectat nu există.");
        }
        if (course.getRoomID() == null || roomService.findById(course.getRoomID()) == null) {
            throw new IllegalArgumentException("Sala selectată nu există.");
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

    public boolean delete(String id) {
        if (id == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Cursul nu există.");
        }
        Course course = repository.findById(id).orElse(null);
        if (course == null) {
            throw new IllegalArgumentException("Cursul nu există.");
        }

        // Business: cannot delete if assignments exist
        if (course.getAssignments() != null && !course.getAssignments().isEmpty()) {
            throw new IllegalArgumentException("Nu se poate șterge cursul deoarece are atribuiri de predare asociate.");
        }

        repository.deleteById(id);
        return true;
    }
}
