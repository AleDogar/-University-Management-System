package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    // SINGURA metodă de filtrare necesară
    @Query("SELECT d FROM Department d WHERE " +
            "(:departmentName IS NULL OR LOWER(d.departmentName) LIKE LOWER(CONCAT('%', :departmentName, '%'))) AND " +
            "(:phoneNumber IS NULL OR LOWER(d.phoneNumber) LIKE LOWER(CONCAT('%', :phoneNumber, '%')))")
    List<Department> findByFilters(
            @Param("departmentName") String departmentName,
            @Param("phoneNumber") String phoneNumber);
}