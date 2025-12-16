package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    // Metode individuale simple (opționale)
    List<Student> findByStudentNameContainingIgnoreCase(String studentName);
    List<Student> findByEmailContainingIgnoreCase(String email);

    // Metoda de filtrare avansată pentru pagina principală
    @Query("SELECT s FROM Student s WHERE " +
            "(:studentName IS NULL OR LOWER(s.studentName) LIKE LOWER(CONCAT('%', :studentName, '%'))) AND " +
            "(:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    List<Student> findByFilters(
            @Param("studentName") String studentName,
            @Param("email") String email);
}