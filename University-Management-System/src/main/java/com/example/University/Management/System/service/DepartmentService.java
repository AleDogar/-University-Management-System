package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DepartmentService {


    private DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public boolean create(Department department) {
        return repository.create(department);
    }

    public Map<String, Department> findAll() {
        return repository.findAll();
    }

    public Department findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, Department student) {
        return repository.update(id, student);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}