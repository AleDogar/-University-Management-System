package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.TeachingAssignment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class TeachingAssignmentRepository extends InFileRepository<TeachingAssignment> {

    public TeachingAssignmentRepository(ObjectMapper mapper,
                                        @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "teachingassignments.json");
    }

    @Override
    protected String getId(TeachingAssignment entity) {
        return entity.getId();
    }

    @Override
    protected void setId(TeachingAssignment entity, String id) {
        entity.setId(id);
    }
}
