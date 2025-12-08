package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AssistantService {

    private final AssistantRepository repository;

    public AssistantService(AssistantRepository repository) {
        this.repository = repository;
    }

    // TOATE METODELE TALE EXISTENTE RĂMÂN LA FEL!
    public boolean create(Assistant assistant) {
        return repository.create(assistant);
    }

    public Map<String, Assistant> findAll() {
        return repository.findAll();
    }

    public Assistant findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, Assistant assistant) {
        return repository.update(id, assistant);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}