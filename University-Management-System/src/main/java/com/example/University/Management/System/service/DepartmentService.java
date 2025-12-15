package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // Creare departament
    public boolean create(Department department) {
        // Trim ID și alte câmpuri pentru siguranță
        if (department.getDepartmentID() != null) {
            department.setDepartmentID(department.getDepartmentID().trim());
        }
        if (department.getDepartmentName() != null) {
            department.setDepartmentName(department.getDepartmentName().trim());
        }
        if (department.getPhoneNumber() != null) {
            department.setPhoneNumber(department.getPhoneNumber().trim());
        }

        // Debug pentru ID
        System.out.println("Creating Department with ID: " + department.getDepartmentID());

        // Business validation: ID unic
        if (repository.existsById(department.getDepartmentID())) {
            System.out.println("ID already exists: " + department.getDepartmentID());
            return false; // ID-ul există deja
        }

        repository.save(department);
        System.out.println("Department saved: " + department);
        return true;
    }

    // Obținerea tuturor departamentelor
    public Map<String, Department> findAll() {
        List<Department> list = repository.findAll();
        Map<String, Department> map = new HashMap<>();
        for (Department d : list) {
            map.put(d.getDepartmentID(), d);
        }
        return map;
    }

    // Obținere departament după ID
    public Department findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update departament
    public boolean update(String id, Department department) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: Department ID does not exist -> " + id);
            return false; // Departamentul nu există
        }

        // Trim ID și alte câmpuri
        department.setDepartmentID(id.trim());
        if (department.getDepartmentName() != null) {
            department.setDepartmentName(department.getDepartmentName().trim());
        }
        if (department.getPhoneNumber() != null) {
            department.setPhoneNumber(department.getPhoneNumber().trim());
        }

        repository.save(department);
        System.out.println("Department updated: " + department);
        return true;
    }

    // Ștergere departament
    public boolean delete(String id) {
        if (id == null) return false;

        Department dept = repository.findById(id.trim()).orElse(null);
        if (dept == null) {
            System.out.println("Delete failed: Department ID not found -> " + id);
            return false; // Departamentul nu există
        }

        // Business validation: nu putem șterge departamente care au cursuri sau profesori
        if ((dept.getCourses() != null && !dept.getCourses().isEmpty()) ||
                (dept.getTeachers() != null && !dept.getTeachers().isEmpty())) {
            System.out.println("Delete failed: Department has related courses or teachers -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Department deleted: " + id);
        return true;
    }
}