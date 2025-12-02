
package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.TeachingAssignment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Profile("infile")
@Repository

public class TeachingAssignmentRepository extends DatabaseRepository<TeachingAssignment> {
    protected TeachingAssignmentRepository(JpaRepository<TeachingAssignment, String> jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected String getIdFromEntity(TeachingAssignment entity) {
        return entity.getId();
    }
}
