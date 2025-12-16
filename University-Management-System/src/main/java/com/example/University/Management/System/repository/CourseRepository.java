package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    // SINGURA metodă de filtrare necesară
    @Query("SELECT c FROM Course c WHERE " +
            "(:title IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:minCredits IS NULL OR c.credits >= :minCredits) AND " +
            "(:maxCredits IS NULL OR c.credits <= :maxCredits) AND " +
            "(:departmentID IS NULL OR LOWER(c.departmentID) LIKE LOWER(CONCAT('%', :departmentID, '%'))) AND " +
            "(:roomID IS NULL OR LOWER(c.roomID) LIKE LOWER(CONCAT('%', :roomID, '%')))")
    List<Course> findByFilters(
            @Param("title") String title,
            @Param("minCredits") Integer minCredits,
            @Param("maxCredits") Integer maxCredits,
            @Param("departmentID") String departmentID,
            @Param("roomID") String roomID);
}