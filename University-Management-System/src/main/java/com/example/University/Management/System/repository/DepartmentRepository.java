package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Department;
import com.example.University.Management.System.repository.interfaces.DepartmentJpaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("infile")
@Repository

public class DepartmentRepository extends DatabaseRepository<Department> {
    protected DepartmentRepository(DepartmentJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected String getIdFromEntity(Department entity) {
        return entity.getDepartmentID();
    }
}
