package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Course;
import com.example.University.Management.System.repository.interfaces.CourseJpaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("infile")
@Repository

public class CourseRepository extends DatabaseRepository<Course> {
    protected CourseRepository(CourseJpaRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected String getIdFromEntity(Course entity) {
        return entity.getCourseID();
    }
}
