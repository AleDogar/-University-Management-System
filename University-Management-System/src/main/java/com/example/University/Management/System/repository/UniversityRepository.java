package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.University;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityRepository extends InMemoryRepository<University> {

    @Override
    protected String getId(University university) {
        return university.getId();
    }

    @Override
    protected void setId(University university, String id) {
        university.setId(id);
    }
}
