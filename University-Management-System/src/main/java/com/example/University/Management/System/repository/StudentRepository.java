package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Student;
import com.example.University.Management.System.repository.DatabaseRepository;
import com.example.University.Management.System.repository.InFileRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("infile")
@Repository

public class StudentRepository extends DatabaseRepository<Student> {
    protected StudentRepository(JpaRepository<Student, String> jpaRepository) {
        super(jpaRepository);
    }

    @Override
    protected String getIdFromEntity(Student entity) {
        return entity.getId();
    }
}
