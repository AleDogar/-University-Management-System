package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Assistant;
import org.springframework.stereotype.Repository;

@Repository
public class AssistantRepository extends InMemoryRepository<Assistant> {

    @Override
    protected String getId(Assistant assistant) {
        return assistant.getStaffID();
    }

    @Override
    protected void setId(Assistant assistant, String id) {
        assistant.setStaffID(id);
    }
}