package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.CourseRepository;
import com.example.University.Management.System.repository.DepartmentRepository;
import com.example.University.Management.System.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final DepartmentRepository departmentRepository;
    private final RoomRepository roomRepository;

    public CourseService(CourseRepository repository,
                         DepartmentRepository departmentRepository,
                         RoomRepository roomRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
    }

    // Creare curs
    public boolean create(Course course) {
        // Trim ID și alte câmpuri pentru siguranță
        if (course.getCourseID() != null) {
            course.setCourseID(course.getCourseID().trim());
        }
        if (course.getTitle() != null) {
            course.setTitle(course.getTitle().trim());
        }
        if (course.getDepartmentID() != null) {
            course.setDepartmentID(course.getDepartmentID().trim());
        }
        if (course.getRoomID() != null) {
            course.setRoomID(course.getRoomID().trim());
        }

        // Debug pentru ID
        System.out.println("Creating Course with ID: " + course.getCourseID());

        // Business validation 1: ID unic
        if (repository.existsById(course.getCourseID())) {
            System.out.println("ID already exists: " + course.getCourseID());
            return false;
        }

        // Business validation 2: Departamentul trebuie să existe
        if (!departmentRepository.existsById(course.getDepartmentID())) {
            System.out.println("Department ID does not exist: " + course.getDepartmentID());
            return false;
        }

        // Business validation 3: Sala trebuie să existe
        if (!roomRepository.existsById(course.getRoomID())) {
            System.out.println("Room ID does not exist: " + course.getRoomID());
            return false;
        }

        repository.save(course);
        System.out.println("Course saved: " + course);
        return true;
    }

    // Obținerea tuturor cursurilor
    public Map<String, Course> findAll() {
        List<Course> list = repository.findAll();
        Map<String, Course> map = new HashMap<>();
        for (Course c : list) {
            map.put(c.getCourseID(), c);
        }
        return map;
    }

    // Obținere curs după ID
    public Course findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update curs
    public boolean update(String id, Course course) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Course ID does not exist -> " + id);
            return false;
        }

        // Trim câmpuri
        course.setCourseID(id.trim());
        if (course.getTitle() != null) {
            course.setTitle(course.getTitle().trim());
        }
        if (course.getDepartmentID() != null) {
            course.setDepartmentID(course.getDepartmentID().trim());
        }
        if (course.getRoomID() != null) {
            course.setRoomID(course.getRoomID().trim());
        }

        // Business validation: Departamentul trebuie să existe
        if (!departmentRepository.existsById(course.getDepartmentID())) {
            System.out.println("Update failed: Department ID does not exist -> " + course.getDepartmentID());
            return false;
        }

        // Business validation: Sala trebuie să existe
        if (!roomRepository.existsById(course.getRoomID())) {
            System.out.println("Update failed: Room ID does not exist -> " + course.getRoomID());
            return false;
        }

        repository.save(course);
        System.out.println("Course updated: " + course);
        return true;
    }

    // Ștergere curs
    public boolean delete(String id) {
        if (id == null) return false;

        Course course = repository.findById(id.trim()).orElse(null);
        if (course == null) {
            System.out.println("Delete failed: Course ID not found -> " + id);
            return false;
        }

        // Business validation: nu putem șterge cursuri care au atribuiri de predare
        if (course.getAssignments() != null && !course.getAssignments().isEmpty()) {
            System.out.println("Delete failed: Course has teaching assignments -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Course deleted: " + id);
        return true;
    }

    // Verificare dacă departamentul există (pentru validare în controller)
    public boolean departmentExists(String departmentID) {
        if (departmentID == null) return false;
        return departmentRepository.existsById(departmentID.trim());
    }

    // Verificare dacă sala există (pentru validare în controller)
    public boolean roomExists(String roomID) {
        if (roomID == null) return false;
        return roomRepository.existsById(roomID.trim());
    }
}