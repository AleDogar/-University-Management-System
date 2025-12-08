package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Assistant;
import com.example.University.Management.System.repository.AssistantRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssistantService {

    private final AssistantRepository repository;

    public AssistantService(AssistantRepository repository) {
        this.repository = repository;
    }

    public boolean create(Assistant assistant) {
        if (repository.existsById(assistant.getStaffID())) {
            return false;
        }
        repository.save(assistant);
        return true;
    }

    public Map<String, Assistant> findAll() {
        List<Assistant> list = repository.findAll();
        Map<String, Assistant> map = new HashMap<>();
        for (Assistant a : list) {
            map.put(a.getStaffID(), a);
        }
        return map;
    }

    public Assistant findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, Assistant assistant) {
        if (!repository.existsById(id)) {
            return false;
        }
        assistant.setStaffID(id);
        repository.save(assistant);
        return true;
    }

    public boolean delete(String id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
