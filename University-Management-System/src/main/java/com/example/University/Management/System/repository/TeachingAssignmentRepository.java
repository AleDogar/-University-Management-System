package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.TeachingAssignment;
import org.springframework.stereotype.Repository;

@Repository
public class TeachingAssignmentRepository extends InMemoryRepository<TeachingAssignment> {

    @Override
    protected String getId(TeachingAssignment teachingAssignment) {
        return teachingAssignment.getTeachingassignmentID();
    }

    @Override
    protected void setId(TeachingAssignment teachingAssignment, String id) {
        teachingAssignment.setTeachingassignmentID(id);
    }
}
