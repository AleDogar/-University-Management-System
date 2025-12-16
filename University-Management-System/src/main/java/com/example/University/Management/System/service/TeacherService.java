package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.repository.TeacherRepository;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // Trim ID și alte câmpuri
        if (teacher.getStaffID() != null) {
            teacher.setStaffID(teacher.getStaffID().trim());
        }
        if (teacher.getStaffName() != null) {
            teacher.setStaffName(teacher.getStaffName().trim());
        }
        if (teacher.getDepartmentID() != null) {
            teacher.setDepartmentID(teacher.getDepartmentID().trim());
        }
        if (teacher.getEmail() != null) {
            teacher.setEmail(teacher.getEmail().trim());
        }

        System.out.println("Creating Teacher with ID: " + teacher.getStaffID());

        // Business validation 1: ID unic
        if (repository.existsById(teacher.getStaffID())) {
            System.out.println("ID already exists: " + teacher.getStaffID());
            return false;
        }

        // Business validation 2: Departamentul trebuie să existe
        if (!departmentRepository.existsById(teacher.getDepartmentID())) {
            System.out.println("Department does not exist: " + teacher.getDepartmentID());
            return false;
        }

        repository.save(teacher);
        System.out.println("Teacher saved: " + teacher);
        return true;
    }

    // Obținerea tuturor profesorilor
    public Map<String, Teacher> findAll() {
        List<Teacher> list = repository.findAll();
        Map<String, Teacher> map = new HashMap<>();
        for (Teacher teacher : list) {
            map.put(teacher.getStaffID(), teacher);
        }
        return map;
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

        // Trim câmpuri
        teacher.setStaffID(id.trim());
        if (teacher.getStaffName() != null) {
            teacher.setStaffName(teacher.getStaffName().trim());
        }
        if (teacher.getDepartmentID() != null) {
            teacher.setDepartmentID(teacher.getDepartmentID().trim());
        }
        if (teacher.getEmail() != null) {
            teacher.setEmail(teacher.getEmail().trim());
        }

        // Business validation: Departamentul trebuie să existe
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

        // Business validation: nu putem șterge profesori care au asignări
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