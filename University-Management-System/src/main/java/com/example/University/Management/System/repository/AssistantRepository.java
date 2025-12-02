package com.example.University.Management.System.repository;

import com.example.University.Management.System.model.Assistant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("infile")
public class AssistantRepository extends DatabaseRepository<Assistant> {

    protected AssistantRepository(JpaRepository<Assistant,String> jpaRepository) {
        super(jpaRepository);

    }

  //  public AssistantRepository(ObjectMapper mapper,
  //@Value("${app.data-folder}") String folder) {
    //     super(mapper, folder, "assistants.json");
    //}

    @Override
    protected String getIdFromEntity(Assistant entity) {
        return entity.getStaffID();
    }

}
