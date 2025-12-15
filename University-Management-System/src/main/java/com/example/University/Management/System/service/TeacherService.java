package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public boolean create(Teacher teacher) {
        if (repository.existsById(teacher.getStaffID())) {
            return false;
        }
        repository.save(teacher);
        return true;
    }

    public Map<String, Teacher> findAll() {
        List<Teacher> list = repository.findAll();
        Map<String, Teacher> map = new HashMap<>();
        for (Teacher teacher : list) {
            map.put(teacher.getStaffID(), teacher);
        }
        return map;
    }

    public Teacher findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(String id, Teacher teacher) {
        if (!repository.existsById(id)) {
            return false;
        }
        teacher.setStaffID(id);
        repository.save(teacher);
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