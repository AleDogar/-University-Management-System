package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.CourseRepository;
import com.example.University.Management.System.repository.DepartmentRepository;
import com.example.University.Management.System.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        System.out.println("Creating Course with ID: " + course.getCourseID());

        if (repository.existsById(course.getCourseID())) {
            System.out.println("ID already exists: " + course.getCourseID());
            return false;
        }

        if (!departmentRepository.existsById(course.getDepartmentID())) {
            System.out.println("Department ID does not exist: " + course.getDepartmentID());
            return false;
        }

        if (!roomRepository.existsById(course.getRoomID())) {
            System.out.println("Room ID does not exist: " + course.getRoomID());
            return false;
        }

        repository.save(course);
        System.out.println("Course saved: " + course);
        return true;
    }

    // Obținerea tuturor cursurilor (pentru dropdowns sau alte nevoi)
    public Map<String, Course> findAll() {
        List<Course> list = repository.findAll();
        Map<String, Course> map = new HashMap<>();
        for (Course c : list) {
            map.put(c.getCourseID(), c);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru cursuri (folosește doar findByFilters)
    public List<Course> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                 String filterTitle, Integer minCredits,
                                                 Integer maxCredits, String filterDepartmentID,
                                                 String filterRoomID) {

        List<Course> courses;

        // Transformăm string-urile goale în null pentru query
        String titleParam = (filterTitle != null && !filterTitle.trim().isEmpty()) ?
                filterTitle.trim() : null;
        String departmentParam = (filterDepartmentID != null && !filterDepartmentID.trim().isEmpty()) ?
                filterDepartmentID.trim() : null;
        String roomParam = (filterRoomID != null && !filterRoomID.trim().isEmpty()) ?
                filterRoomID.trim() : null;

        // 1. FILTRARE folosind metoda principală
        courses = repository.findByFilters(
                titleParam, minCredits, maxCredits, departmentParam, roomParam
        );

        // 2. SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Course> comparator = getComparator(sortBy, sortDir);
            courses = courses.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return courses;
    }

    // Comparator pentru sortare
    private Comparator<Course> getComparator(String sortBy, String sortDir) {
        Comparator<Course> comparator;

        switch (sortBy) {
            case "courseID":
                comparator = Comparator.comparing(Course::getCourseID);
                break;
            case "title":
                comparator = Comparator.comparing(Course::getTitle, String.CASE_INSENSITIVE_ORDER);
                break;
            case "credits":
                comparator = Comparator.comparing(Course::getCredits);
                break;
            case "departmentID":
                comparator = Comparator.comparing(Course::getDepartmentID, String.CASE_INSENSITIVE_ORDER);
                break;
            case "roomID":
                comparator = Comparator.comparing(Course::getRoomID, String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(Course::getCourseID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
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

        if (!departmentRepository.existsById(course.getDepartmentID())) {
            System.out.println("Update failed: Department ID does not exist -> " + course.getDepartmentID());
            return false;
        }

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

        if (course.getAssignments() != null && !course.getAssignments().isEmpty()) {
            System.out.println("Delete failed: Course has teaching assignments -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Course deleted: " + id);
        return true;
    }

    // Verificări pentru validare
    public boolean departmentExists(String departmentID) {
        if (departmentID == null) return false;
        return departmentRepository.existsById(departmentID.trim());
    }

    public boolean roomExists(String roomID) {
        if (roomID == null) return false;
        return roomRepository.existsById(roomID.trim());
    }

    // Metode alternative folosind metoda principală findByFilters
    public List<Course> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return Collections.emptyList();
        }
        // Folosim findByFilters cu doar titlul setat, restul null
        return repository.findByFilters(title.trim(), null, null, null, null);
    }

    public List<Course> findByDepartment(String departmentID) {
        if (departmentID == null || departmentID.trim().isEmpty()) {
            return Collections.emptyList();
        }
        // Folosim findByFilters cu doar departamentul setat, restul null
        return repository.findByFilters(null, null, null, departmentID.trim(), null);
    }
}