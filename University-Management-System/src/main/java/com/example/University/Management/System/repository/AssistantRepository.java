package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Assistant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class AssistantRepository extends InFileRepository<Assistant> {

    public AssistantRepository(ObjectMapper mapper,
                               @Value("${app.data-folder}") String folder) {
        super(mapper, folder, "assistants.json");
    }

    @Override
    protected String getId(Assistant entity) {
        return entity.getStaffID();
    }

    @Override
    protected void setId(Assistant entity, String id) {
        entity.setStaffID(id);
    }
}
