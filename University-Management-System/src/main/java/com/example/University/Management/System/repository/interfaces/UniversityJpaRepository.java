package com.example.University.Management.System.repository.interfaces;

import com.example.University.Management.System.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityJpaRepository extends JpaRepository<University, String> {
}
