package com.example.University.Management.System.service;

import com.example.University.Management.System.model.Teacher;
import com.example.University.Management.System.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(String id) {
        return teacherRepository.findById(id);
    }

    public Teacher saveTeacher(Teacher teacher) {
        if (teacher.getStaffID() == null || teacher.getStaffID().isEmpty()) {
            teacher.setStaffID(UUID.randomUUID().toString());
        }
        teacherRepository.save(teacher);
        return teacher;
    }

    public void deleteTeacher(String id) {
        teacherRepository.deleteById(id);
    }
}
