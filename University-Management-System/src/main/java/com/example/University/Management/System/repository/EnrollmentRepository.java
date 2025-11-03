package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Enrollment;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository extends InMemoryRepository<Enrollment> {
    @Override
    protected String getId(Enrollment entity) { return entity.getEnrollmentID(); }
    @Override
    protected void setId(Enrollment entity, String id) { entity.setEnrollmentID(id); }
}
