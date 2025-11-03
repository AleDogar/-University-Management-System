package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.TeachingAssignment;
import org.springframework.stereotype.Repository;

@Repository
public class TeachingAssignmentRepository extends InMemoryRepository<TeachingAssignment> {
    @Override
    protected String getId(TeachingAssignment entity) { return entity.getTeachingassignmentID(); }
    @Override
    protected void setId(TeachingAssignment entity, String id) { entity.setTeachingassignmentID(id); }
}
