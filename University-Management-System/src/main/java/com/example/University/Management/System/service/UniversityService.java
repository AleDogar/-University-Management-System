package com.example.University.Management.System.service;

import com.example.University.Management.System.model.University;
import com.example.University.Management.System.repository.UniversityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    private final UniversityRepository repository;

    public UniversityService(UniversityRepository repository) {
        this.repository = repository;
    }

    // Creare universitate
    public boolean create(University university) {
        if (university.getUniversityID() != null) {
            university.setUniversityID(university.getUniversityID().trim());
        }
        if (university.getUniversityName() != null) {
            university.setUniversityName(university.getUniversityName().trim());
        }
        if (university.getCity() != null) {
            university.setCity(university.getCity().trim());
        }

        System.out.println("Creating University with ID: " + university.getUniversityID());

        if (repository.existsById(university.getUniversityID())) {
            System.out.println("ID already exists: " + university.getUniversityID());
            return false;
        }

        repository.save(university);
        System.out.println("University saved: " + university);
        return true;
    }

    // Obținere toate universitățile (fără filtrare/sortare)
    public Map<String, University> findAll() {
        List<University> list = repository.findAll();
        Map<String, University> map = new HashMap<>();
        for (University u : list) {
            map.put(u.getUniversityID(), u);
        }
        return map;
    }

    // SORTARE + FILTRARE
    public List<University> findAllWithSortAndFilter(String sortBy, String sortDir,
                                                     String filterName, String filterCity) {
        List<University> universities;

        // 1. FILTRARE
        if ((filterName != null && !filterName.trim().isEmpty()) ||
                (filterCity != null && !filterCity.trim().isEmpty())) {

            // Dacă ambele filtre sunt completate
            if (filterName != null && !filterName.trim().isEmpty() &&
                    filterCity != null && !filterCity.trim().isEmpty()) {
                universities = repository.findByUniversityNameContainingIgnoreCaseAndCityContainingIgnoreCase(
                        filterName.trim(), filterCity.trim());
            }
            // Doar filtru după nume
            else if (filterName != null && !filterName.trim().isEmpty()) {
                universities = repository.findByUniversityNameContainingIgnoreCase(filterName.trim());
            }
            // Doar filtru după oraș
            else {
                universities = repository.findByCityContainingIgnoreCase(filterCity.trim());
            }
        } else {
            // Fără filtre - returnează toate
            universities = repository.findAll();
        }

        // 2. SORTARE
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<University> comparator = getComparator(sortBy, sortDir);
            universities = universities.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return universities;
    }

    // Comparator pentru sortare
    private Comparator<University> getComparator(String sortBy, String sortDir) {
        Comparator<University> comparator;

        switch (sortBy) {
            case "universityID":
                comparator = Comparator.comparing(University::getUniversityID);
                break;
            case "universityName":
                comparator = Comparator.comparing(University::getUniversityName,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            case "city":
                comparator = Comparator.comparing(University::getCity,
                        String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(University::getUniversityID);
        }

        // Direcția sortării (ascending/descending)
        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    // Obținere universitate după ID
    public University findById(String id) {
        if (id == null) return null;
        return repository.findById(id.trim()).orElse(null);
    }

    // Update universitate
    public boolean update(String id, University university) {
        if (id == null || !repository.existsById(id.trim())) {
            System.out.println("Update failed: University ID does not exist -> " + id);
            return false;
        }

        university.setUniversityID(id.trim());
        if (university.getUniversityName() != null) {
            university.setUniversityName(university.getUniversityName().trim());
        }
        if (university.getCity() != null) {
            university.setCity(university.getCity().trim());
        }

        repository.save(university);
        System.out.println("University updated: " + university);
        return true;
    }

    // Ștergere universitate
    public boolean delete(String id) {
        if (id == null) return false;

        University uni = repository.findById(id.trim()).orElse(null);
        if (uni == null) {
            System.out.println("Delete failed: University ID not found -> " + id);
            return false;
        }

        if ((uni.getDepartments() != null && !uni.getDepartments().isEmpty()) ||
                (uni.getRooms() != null && !uni.getRooms().isEmpty())) {
            System.out.println("Delete failed: University has related departments or rooms -> " + id);
            return false;
        }

        repository.deleteById(id.trim());
        System.out.println("University deleted: " + id);
        return true;
    }
}