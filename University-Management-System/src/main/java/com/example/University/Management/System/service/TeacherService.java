package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repo;
    public TeacherService(TeacherRepository repo) { this.repo = repo; }
    public List<Teacher> getAllTeachers() { return repo.findAll(); }
    public Teacher addTeacher(Teacher t) { return repo.save(t); }
    public void removeTeacher(String id) { repo.deleteById(id); }
}
