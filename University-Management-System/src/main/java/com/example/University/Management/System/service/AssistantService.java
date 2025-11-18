package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistantService {

    private final AssistantRepository assistantRepository;

    public AssistantService(AssistantRepository assistantRepository)
    {

        this.assistantRepository = assistantRepository;
    }

    public List<Assistant> getAllAssistants()
    {

        return assistantRepository.findAll();
    }

    public Assistant addAssistant(Assistant assistant)
    {
        return assistantRepository.save(assistant);
    }

    public void removeAssistant(String id)
    {

        assistantRepository.deleteById(id);
    }
}
