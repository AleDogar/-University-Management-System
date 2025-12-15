package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.TeachingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment, String> {
}
