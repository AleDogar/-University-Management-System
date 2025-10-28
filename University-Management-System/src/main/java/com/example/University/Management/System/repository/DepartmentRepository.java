package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository extends InMemoryRepository<Department> {

    @Override
    protected String getId(Department department) {
        return department.getDepartmentID();
    }

    @Override
    protected void setId(Department department, String id) {
        department.setDepartmentID(id);
    }
}