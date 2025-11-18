package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssistantService {

    private final AssistantRepository assistantRepository;

    public AssistantService(AssistantRepository assistantRepository) {
        this.assistantRepository = assistantRepository;
    }

    public List<Assistant> getAllAssistants() {
        return assistantRepository.findAll();
    }

    public Assistant getAssistantById(String id) {
        return assistantRepository.findById(id);
    }

    public Assistant saveAssistant(Assistant assistant) {
        // generează ID dacă nu există
        if (assistant.getStaffID() == null || assistant.getStaffID().isEmpty()) {
            assistant.setStaffID(UUID.randomUUID().toString());
        }
        assistantRepository.save(assistant);
        return assistant;
    }

    public void deleteAssistant(String id) {
        assistantRepository.deleteById(id);
    }
}
