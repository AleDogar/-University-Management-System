package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    // Metode individuale simple
    List<Teacher> findByStaffNameContainingIgnoreCase(String staffName);
    List<Teacher> findByTitleContainingIgnoreCase(String title);
    List<Teacher> findByDepartmentIDContainingIgnoreCase(String departmentID);
    List<Teacher> findByEmailContainingIgnoreCase(String email);

    // Metoda de filtrare avansată pentru pagina principală
    @Query("SELECT t FROM Teacher t WHERE " +
            "(:staffName IS NULL OR LOWER(t.staffName) LIKE LOWER(CONCAT('%', :staffName, '%'))) AND " +
            "(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:departmentID IS NULL OR LOWER(t.departmentID) LIKE LOWER(CONCAT('%', :departmentID, '%'))) AND " +
            "(:email IS NULL OR LOWER(t.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    List<Teacher> findByFilters(
            @Param("staffName") String staffName,
            @Param("title") String title,
            @Param("departmentID") String departmentID,
            @Param("email") String email);
}