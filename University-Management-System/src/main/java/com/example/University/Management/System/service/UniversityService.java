package com.example.University.Management.System.service;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public void addUniversity(University university) {
        universityRepository.save(university);
    }

    public void removeUniversity(String universityId) {
        University university = universityRepository.findById(universityId);
        if (university != null) {
            universityRepository.delete(university);
        }
    }

    public University getUniversityById(String id) {
        return universityRepository.findById(id);
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}
