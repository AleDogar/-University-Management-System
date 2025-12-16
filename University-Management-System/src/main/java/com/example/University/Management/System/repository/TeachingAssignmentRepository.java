package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.TeachingAssignment;
import com.example.University.Management.System.model.ClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment, String> {

    // SINGURA metodă de filtrare necesară
    @Query("SELECT ta FROM TeachingAssignment ta WHERE " +
            "(:classType IS NULL OR ta.classType = :classType) AND " +
            "(:staffID IS NULL OR LOWER(ta.staffID) LIKE LOWER(CONCAT('%', :staffID, '%'))) AND " +
            "(:courseID IS NULL OR LOWER(ta.course.courseID) LIKE LOWER(CONCAT('%', :courseID, '%')))")
    List<TeachingAssignment> findByFilters(
            @Param("classType") ClassType classType,
            @Param("staffID") String staffID,
            @Param("courseID") String courseID);
}