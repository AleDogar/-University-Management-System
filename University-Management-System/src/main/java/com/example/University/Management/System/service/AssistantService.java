package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AssistantService {


    private AssistantRepository repository;

    @Autowired
    public AssistantService(AssistantRepository repository) {
        this.repository = repository;
    }

    public boolean create(Assistant assistant) {
        return repository.create(assistant);
    }

    public Map<String, Assistant> findAll() {
        return repository.findAll();
    }

    public Assistant findById(String id) {
        return repository.findById(id);
    }

    public boolean update(String id, Assistant student) {
        return repository.update(id, student);
    }

    public boolean delete(String id) {
        return repository.delete(id);
    }
}