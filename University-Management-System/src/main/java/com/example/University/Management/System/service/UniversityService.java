package com.example.University.Management.System.service;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversityById(String id) {
        return universityRepository.findById(id);
    }

    public University saveUniversity(University university) {
        // păstrează ID-ul dacă există, altfel generează unul
        if (university.getUniversityID() == null || university.getUniversityID().isEmpty()) {
            university.setUniversityID(UUID.randomUUID().toString());
        }
        universityRepository.save(university);
        return university;
    }

    public void deleteUniversity(String id) {
        universityRepository.deleteById(id);
    }
}