package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends InMemoryRepository<Course> {

    @Override
    protected String getId(Course course) {
        return course.getCourseID();
    }

    @Override
    protected void setId(Course course, String id) {
        course.setCourseID(id);
    }
}