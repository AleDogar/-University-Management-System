package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Enrollment;
import com.example.University.Management.System.repository.interfaces.EnrollmentJpaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

//@Profile("infile")
@Repository

public class EnrollmentRepository extends DatabaseRepository<Enrollment> {
    protected EnrollmentRepository(EnrollmentJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected String getIdFromEntity(Enrollment entity) {
        return entity.getEnrollmentID();
    }
}
