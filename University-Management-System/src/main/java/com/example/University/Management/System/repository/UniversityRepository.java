package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.University;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class UniversityRepository extends InFileRepository<University> {

    public UniversityRepository(ObjectMapper mapper,
                                @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "universities.json");
    }

    @Override
    protected String getId(University entity) {
        return entity.getUniversityID();
    }

    @Override
    protected void setId(University entity, String id) {
        entity.setUniversityID(id);
    }
}