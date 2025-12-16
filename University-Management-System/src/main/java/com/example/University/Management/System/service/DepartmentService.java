package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // Creare departament
    public boolean create(Department department) {
        if (department.getDepartmentID() != null) {
            department.setDepartmentID(department.getDepartmentID().trim());
        }
        if (department.getDepartmentName() != null) {
            department.setDepartmentName(department.getDepartmentName().trim());
        }
        if (department.getPhoneNumber() != null) {
            department.setPhoneNumber(department.getPhoneNumber().trim());
        }

        System.out.println("Creating Department with ID: " + department.getDepartmentID());

        if (repository.existsById(department.getDepartmentID())) {
            System.out.println("ID already exists: " + department.getDepartmentID());
            return false;
        }

        repository.save(department);
        System.out.println("Department saved: " + department);
        return true;
    }

    // Obținerea tuturor departamentelor (pentru dropdowns sau alte nevoi)
    public Map<String, Department> findAll() {
        List<Department> list = repository.findAll();
        Map<String, Department> map = new HashMap<>();
        for (Department d : list) {
            map.put(d.getDepartmentID(), d);
        }
        return map;
    }

    // SORTARE + FILTRARE pentru departamente
    public List<Department> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                     String filterDepartmentName, String filterPhoneNumber) {

        List<Department> departments;

        // Transformăm string-urile goale în null pentru query
        String nameParam = (filterDepartmentName != null && !filterDepartmentName.trim().isEmpty()) ?
                filterDepartmentName.trim() : null;
        String phoneParam = (filterPhoneNumber != null && !filterPhoneNumber.trim().isEmpty()) ?
                filterPhoneNumber.trim() : null;

        // FILTRARE folosind metoda principală
        departments = repository.findByFilters(nameParam, phoneParam);

        // SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Department> comparator = getComparator(sortBy, sortDir);
            departments = departments.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return departments;
    }

    // Comparator pentru sortare
    private Comparator<Department> getComparator(String sortBy, String sortDir) {
        Comparator<Department> comparator;

        switch (sortBy) {
            case "departmentID":
                comparator = Comparator.comparing(Department::getDepartmentID);
                break;
            case "departmentName":
                comparator = Comparator.comparing(Department::getDepartmentName,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "phoneNumber":
                comparator = Comparator.comparing(Department::getPhoneNumber);
                break;
            default:
                comparator = Comparator.comparing(Department::getDepartmentID);
        }

        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
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
            return false;
        }

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
            return false;
        }

        if ((dept.getCourses() != null && !dept.getCourses().isEmpty()) ||
                (dept.getTeachers() != null && !dept.getTeachers().isEmpty())) {
            System.out.println("Delete failed: Department has related courses or teachers -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("Department deleted: " + id);
        return true;
    }

    // Metode alternative folosind metoda principală (dacă sunt necesare)
    public List<Department> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Collections.emptyList();
        }
        // Folosim findByFilters cu doar numele setat
        return repository.findByFilters(name.trim(), null);
    }

    public List<Department> searchByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return Collections.emptyList();
        }
        // Folosim findByFilters cu doar telefonul setat
        return repository.findByFilters(null, phone.trim());
    }
}