package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

    // SINGURA metodă de filtrare necesară
    @Query("SELECT e FROM Enrollment e WHERE " +
            "(:enrollmentID IS NULL OR LOWER(e.enrollmentID) LIKE LOWER(CONCAT('%', :enrollmentID, '%'))) AND " +
            "(:studentID IS NULL OR LOWER(e.studentID) LIKE LOWER(CONCAT('%', :studentID, '%'))) AND " +
            "(:courseID IS NULL OR LOWER(e.courseID) LIKE LOWER(CONCAT('%', :courseID, '%'))) AND " +
            "(:grade IS NULL OR LOWER(e.grade) LIKE LOWER(CONCAT('%', :grade, '%')))")
    List<Enrollment> findByFilters(
            @Param("enrollmentID") String enrollmentID,
            @Param("studentID") String studentID,
            @Param("courseID") String courseID,
            @Param("grade") String grade);
}