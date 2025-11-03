package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository extends InMemoryRepository<Student> {
    @Override
    protected String getId(Student entity) { return entity.getStudentID(); }
    @Override
    protected void setId(Student entity, String id) { entity.setStudentID(id); }
}
