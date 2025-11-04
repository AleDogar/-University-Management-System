package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void removeDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}
