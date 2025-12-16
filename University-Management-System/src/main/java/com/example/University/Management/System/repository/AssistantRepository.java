package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.model.ClassRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, String> {

    // Metode individuale simple
    List<Assistant> findByStaffNameContainingIgnoreCase(String staffName);
    List<Assistant> findByRole(ClassRole role);
    List<Assistant> findByEmailContainingIgnoreCase(String email);

    // Metoda de filtrare avansată pentru pagina principală
    @Query("SELECT a FROM Assistant a WHERE " +
            "(:staffName IS NULL OR LOWER(a.staffName) LIKE LOWER(CONCAT('%', :staffName, '%'))) AND " +
            "(:role IS NULL OR a.role = :role) AND " +
            "(:email IS NULL OR LOWER(a.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    List<Assistant> findByFilters(
            @Param("staffName") String staffName,
            @Param("role") ClassRole role,
            @Param("email") String email);
}