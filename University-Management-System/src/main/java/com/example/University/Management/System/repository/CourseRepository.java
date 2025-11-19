package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class CourseRepository extends InFileRepository<Course> {

    public CourseRepository(ObjectMapper mapper,
                            @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "courses.json");
    }

    @Override
    protected String getId(Course entity) {
        return entity.getCourseID();
    }

    @Override
    protected void setId(Course entity, String id) {
        entity.setCourseID(id);
    }
}
