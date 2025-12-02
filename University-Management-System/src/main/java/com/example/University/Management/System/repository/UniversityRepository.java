package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.repository.interfaces.UniversityJpaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

//@Profile("infile")
@Repository

public class UniversityRepository extends DatabaseRepository<University> {
    protected UniversityRepository(UniversityJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected String getIdFromEntity(University entity) {
        return entity.getUniversityID();
    }
}
