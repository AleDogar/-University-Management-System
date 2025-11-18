package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class TeacherRepository extends InFileRepository<Teacher> {

    public TeacherRepository(ObjectMapper mapper,
                             @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "teachers.json");
    }

    @Override
    protected String getId(Teacher entity) {
        return entity.getStaffID();
    }

    @Override
    protected void setId(Teacher entity, String id) {
        entity.setStaffID(id);
    }
}
