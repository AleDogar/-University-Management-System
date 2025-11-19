package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Enrollment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class EnrollmentRepository extends InFileRepository<Enrollment> {

    public EnrollmentRepository(ObjectMapper mapper,
                                @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "enrollments.json");
    }

    @Override
    protected String getId(Enrollment entity) {
        return entity.getEnrollmentID();
    }

    @Override
    protected void setId(Enrollment entity, String id) {
        entity.setEnrollmentID(id);
    }
}
