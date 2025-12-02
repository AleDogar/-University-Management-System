package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Profile("infile")
public class TeacherRepository extends DatabaseRepository<Teacher> {

    protected TeacherRepository(JpaRepository<Teacher,String> jpaRepository) {
        super(jpaRepository);
    }



    @Override
    protected String getIdFromEntity(Teacher entity) {
        return entity.getStaffID();
    }

}
