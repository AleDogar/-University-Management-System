package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.University;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityRepository extends InMemoryRepository<University> {

    @Override
    protected String getId(University entity) {
        return entity.getId(); }

    @Override
    protected void setId(University entity, String id) {
        entity.setId(id); }
}
