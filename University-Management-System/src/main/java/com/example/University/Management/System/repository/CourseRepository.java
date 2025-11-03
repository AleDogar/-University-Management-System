package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends InMemoryRepository<Course> {
    @Override
    protected String getId(Course entity) { return entity.getCourseID(); }
    @Override
    protected void setId(Course entity, String id) { entity.setCourseID(id); }
}
