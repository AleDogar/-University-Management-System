package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    public DepartmentService(DepartmentRepository repo) { this.repo = repo; }
    public List<Department> getAllDepartments() { return repo.findAll(); }
    public Department addDepartment(Department d) { return repo.save(d); }
    public void removeDepartment(String id) { repo.deleteById(id); }
}
