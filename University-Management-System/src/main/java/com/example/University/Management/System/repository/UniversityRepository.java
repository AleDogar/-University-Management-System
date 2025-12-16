package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {

    // Filtrare după oraș
    List<University> findByCityContainingIgnoreCase(String city);

    // Filtrare după nume universitate
    List<University> findByUniversityNameContainingIgnoreCase(String name);

    // Filtrare combinată: nume și oraș
    List<University> findByUniversityNameContainingIgnoreCaseAndCityContainingIgnoreCase(
            String name, String city);

    // Query custom pentru filtrare avansată (opțional, pentru flexibilitate)
    @Query("SELECT u FROM University u WHERE " +
            "(:name IS NULL OR LOWER(u.universityName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:city IS NULL OR LOWER(u.city) LIKE LOWER(CONCAT('%', :city, '%')))")
    List<University> findByFilters(@Param("name") String name, @Param("city") String city);
}