package com.example.University.Management.System.controller;

import com.example.University.Management.System.service.UniversityService;
import org.springframework.web.bind.annotation.GetMapping;

public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Die Anwendung funktioniert!";
    }
}
