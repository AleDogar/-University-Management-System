package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class StudentRepository extends InFileRepository<Student> {

    public StudentRepository(ObjectMapper mapper,
                             @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "students.json");
    }

    @Override
    protected String getId(Student entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Student entity, String id) {
        entity.setId(id);
    }
}
