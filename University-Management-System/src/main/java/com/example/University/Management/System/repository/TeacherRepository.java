package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository extends InMemoryRepository<Teacher> {

    @Override
    protected String getId(Teacher teacher) {
        return teacher.getTitle();
    }

    @Override
    protected void setId(Teacher teacher, String id) {
        teacher.setTitle(id);
    }
}
