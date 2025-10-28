package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Enrollment;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository extends InMemoryRepository<Enrollment> {

    @Override
    protected String getId(Enrollment enrollment) {
        return enrollment.getEnrollmentID();
    }

    @Override
    protected void setId(Enrollment enrollment, String id) {
        enrollment.setEnrollmentID(id);
    }
}