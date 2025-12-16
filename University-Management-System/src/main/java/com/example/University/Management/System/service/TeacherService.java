package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.repository.TeacherRepository;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository repository;
    private final DepartmentRepository departmentRepository;

    public TeacherService(TeacherRepository repository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    // Creare profesor
    public boolean create(Teacher teacher) {
        if (teacher.getStaffID() != null) {
            teacher.setStaffID(teacher.getStaffID().trim());
        }
        if (teacher.getStaffName() != null) {
            teacher.setStaffName(teacher.getStaffName().trim());
        }
        if (teacher.getTitle() != null) {
            teacher.setTitle(teacher.getTitle().trim());
        }
        if (teacher.getDepartmentID() != null) {
            teacher.setDepartmentID(teacher.getDepartmentID().trim());
        }
        if (teacher.getEmail() != null) {
            teacher.setEmail(teacher.getEmail().trim());
        }

        System.out.println("Creating Teacher with ID: " + teacher.getStaffID());

        if (repository.existsById(teacher.getStaffID())) {
            System.out.println("ID already exists: " + teacher.getStaffID());
            return false;
        }

        if (!departmentRepository.existsById(teacher.getDepartmentID())) {
            System.out.println("Department does not exist: " + teacher.getDepartmentID());
            return false;
        }

        repository.save(teacher);
        System.out.println("Teacher saved: " + teacher);
        return true;
    }

    // Obținerea tuturor profesorilor (pentru dropdowns sau alte nevoi)
    public Map<String, Teacher> findAll() {
        List<Teacher> list = repository.findAll();
        Map<String, Teacher> map = new HashMap<>();
        for (Teacher teacher : list) {
            map.put(teacher.getStaffID(), teacher);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru profesori
    public List<Teacher> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                  String filterStaffName, String filterTitle,
                                                  String filterDepartmentID, String filterEmail) {

        List<Teacher> teachers;

        // Transformăm string-urile goale în null pentru query
        String staffNameParam = (filterStaffName != null && !filterStaffName.trim().isEmpty()) ?
                filterStaffName.trim() : null;
        String titleParam = (filterTitle != null && !filterTitle.trim().isEmpty()) ?
                filterTitle.trim() : null;
        String departmentParam = (filterDepartmentID != null && !filterDepartmentID.trim().isEmpty()) ?
                filterDepartmentID.trim() : null;
        String emailParam = (filterEmail != null && !filterEmail.trim().isEmpty()) ?
                filterEmail.trim() : null;

        // FILTRARE folosind metoda principală
        teachers = repository.findByFilters(staffNameParam, titleParam, departmentParam, emailParam);

        // SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Teacher> comparator = getComparator(sortBy, sortDir);
            teachers = teachers.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return teachers;
    }

    // Comparator pentru sortare
    private Comparator<Teacher> getComparator(String sortBy, String sortDir) {
        Comparator<Teacher> comparator;

        switch (sortBy) {
            case "staffID":
                comparator = Comparator.comparing(Teacher::getStaffID);
                break;
            case "staffName":
                comparator = Comparator.comparing(Teacher::getStaffName,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "title":
                comparator = Comparator.comparing(Teacher::getTitle,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "departmentID":
                comparator = Comparator.comparing(Teacher::getDepartmentID,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "email":
                comparator = Comparator.comparing(Teacher::getEmail,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(Teacher::getStaffID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    // Obținere profesor după ID
    public Teacher findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update profesor
    public boolean update(String id, Teacher teacher) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Teacher ID does not exist -> " + id);
            return false;
        }

        teacher.setStaffID(id.trim());
        if (teacher.getStaffName() != null) {
            teacher.setStaffName(teacher.getStaffName().trim());
        }
        if (teacher.getTitle() != null) {
            teacher.setTitle(teacher.getTitle().trim());
        }
        if (teacher.getDepartmentID() != null) {
            teacher.setDepartmentID(teacher.getDepartmentID().trim());
        }
        if (teacher.getEmail() != null) {
            teacher.setEmail(teacher.getEmail().trim());
        }

        if (!departmentRepository.existsById(teacher.getDepartmentID())) {
            System.out.println("Update failed: Department does not exist -> " + teacher.getDepartmentID());
            return false;
        }

        repository.save(teacher);
        System.out.println("Teacher updated: " + teacher);
        return true;
    }

    // Ștergere profesor
    public boolean delete(String id) {
        if (id == null) return false;

        Teacher teacher = repository.findById(id.trim()).orElse(null);
        if (teacher == null) {
            System.out.println("Delete failed: Teacher ID not found -> " + id);
            return false;
        }

        if (teacher.getAssignments() != null && !teacher.getAssignments().isEmpty()) {
            System.out.println("Delete failed: Teacher has assignments -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Teacher deleted: " + id);
        return true;
    }

    // Verificare dacă departamentul există
    public boolean departmentExists(String departmentID) {
        if (departmentID == null) return false;
        return departmentRepository.existsById(departmentID.trim());
    }
}