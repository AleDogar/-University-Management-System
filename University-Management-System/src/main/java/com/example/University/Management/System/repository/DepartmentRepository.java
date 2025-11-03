package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository extends InMemoryRepository<Department> {
    @Override
    protected String getId(Department entity) { return entity.getDepartmentID(); }
    @Override
    protected void setId(Department entity, String id) { entity.setDepartmentID(id); }
}
