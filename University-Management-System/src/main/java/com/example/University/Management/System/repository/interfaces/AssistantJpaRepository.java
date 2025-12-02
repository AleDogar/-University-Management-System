package com.example.University.Management.System.repository.interfaces;

import com.example.University.Management.System.model.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantJpaRepository extends JpaRepository<Assistant, String> {
}
