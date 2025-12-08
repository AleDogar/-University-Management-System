package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, String> {
}
