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

    public boolean create(Department department) {
        if (repository.existsById(department.getDepartmentID())) {
            return false;
        }
        repository.save(department);
        return true;
    }

    public Map<String, Department> findAll() {
        List<Department> list = repository.findAll();
        Map<String, Department> map = new HashMap<>();
        for (Department d : list) {
            map.put(d.getDepartmentID(), d);
        }
        return map;
    }

    public Department findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, Department department) {
        if (!repository.existsById(id)) {
            return false;
        }
        department.setDepartmentID(id);
        repository.save(department);
        return true;
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
