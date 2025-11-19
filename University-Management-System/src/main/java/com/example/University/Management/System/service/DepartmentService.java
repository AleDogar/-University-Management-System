package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department getDepartmentById(String id) {
        return repository.findById(id);
    }

    public Department saveDepartment(Department department) {
        if (department.getDepartmentID() == null || department.getDepartmentID().isEmpty()) {
            department.setDepartmentID(UUID.randomUUID().toString());
        }
        repository.save(department);
        return department;
    }

    public void deleteDepartment(String id) {
        repository.deleteById(id);
    }
}
