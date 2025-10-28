package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistantService {

    private final AssistantRepository assistantRepository;

    public AssistantService(AssistantRepository assistantRepository) {
        this.assistantRepository = assistantRepository;
    }

    public void addAssistant(Assistant assistant) {
        assistantRepository.save(assistant);
    }

    public void removeAssistant(String assistantId) {
        Assistant assistant = assistantRepository.findById(assistantId);
        if (assistant != null) {
            assistantRepository.delete(assistant);
        }
    }

    public Assistant getAssistantById(String id) {
        return assistantRepository.findById(id);
    }

    public List<Assistant> getAllAssistants() {
        return assistantRepository.findAll();
    }
}