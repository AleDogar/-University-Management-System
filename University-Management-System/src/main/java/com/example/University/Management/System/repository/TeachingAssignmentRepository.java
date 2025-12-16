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

    // Filtrare după tipul clasei
    List<TeachingAssignment> findByClassType(ClassType classType);

    // Filtrare după ID profesor
    List<TeachingAssignment> findByStaffIDContainingIgnoreCase(String staffID);

    // Filtrare după ID curs
    List<TeachingAssignment> findByCourse_CourseIDContainingIgnoreCase(String courseID);

    // Filtrare combinată: tip clasă și profesor
    List<TeachingAssignment> findByClassTypeAndStaffIDContainingIgnoreCase(
            ClassType classType, String staffID);

    // Filtrare combinată: tip clasă și curs
    List<TeachingAssignment> findByClassTypeAndCourse_CourseIDContainingIgnoreCase(
            ClassType classType, String courseID);

    // Filtrare combinată: profesor și curs
    List<TeachingAssignment> findByStaffIDContainingIgnoreCaseAndCourse_CourseIDContainingIgnoreCase(
            String staffID, String courseID);

    // Filtrare completă
    List<TeachingAssignment> findByClassTypeAndStaffIDContainingIgnoreCaseAndCourse_CourseIDContainingIgnoreCase(
            ClassType classType, String staffID, String courseID);

    // Query custom pentru filtrare flexibilă
    @Query("SELECT ta FROM TeachingAssignment ta WHERE " +
            "(:classType IS NULL OR ta.classType = :classType) AND " +
            "(:staffID IS NULL OR LOWER(ta.staffID) LIKE LOWER(CONCAT('%', :staffID, '%'))) AND " +
            "(:courseID IS NULL OR LOWER(ta.course.courseID) LIKE LOWER(CONCAT('%', :courseID, '%')))")
    List<TeachingAssignment> findByFilters(
            @Param("classType") ClassType classType,
            @Param("staffID") String staffID,
            @Param("courseID") String courseID);
}