package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository extends InMemoryRepository<Teacher> {
    @Override
    protected String getId(Teacher entity) { return entity.getStaffID(); }
    @Override
    protected void setId(Teacher entity, String id) { entity.setStaffID(id); }
}
