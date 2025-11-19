package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Department;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class DepartmentRepository extends InFileRepository<Department> {

    public DepartmentRepository(ObjectMapper mapper,
                                @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "departments.json");
    }

    @Override
    protected String getId(Department entity) {
        return entity.getDepartmentID();
    }

    @Override
    protected void setId(Department entity, String id) {
        entity.setDepartmentID(id);
    }
}
