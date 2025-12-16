package com.example.University.Management.System.controller;

import com.example.University.Management.System.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UniversityService universityService;
    private final CourseService courseService;
    private final RoomService roomService;
    private final TeacherService teacherService;
    private final DepartmentService departmentService;
    private final StudentService studentService;
    private final AssistantService assistantService;
    private final TeachingAssignmentService teachingAssignmentService;
    private final EnrollmentService enrollmentService;

    public HomeController(UniversityService universityService,
                          CourseService courseService,
                          RoomService roomService,
                          TeacherService teacherService,
                          DepartmentService departmentService,
                          StudentService studentService,
                          AssistantService assistantService,
                          TeachingAssignmentService teachingAssignmentService,
                          EnrollmentService enrollmentService) {
        this.universityService = universityService;
        this.courseService = courseService;
        this.roomService = roomService;
        this.teacherService = teacherService;
        this.departmentService = departmentService;
        this.studentService = studentService;
        this.assistantService = assistantService;
        this.teachingAssignmentService = teachingAssignmentService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("countUniversities", universityService.findAll().size());
        model.addAttribute("countCourses", courseService.findAll().size());
        model.addAttribute("countRooms", roomService.findAll().size());
        model.addAttribute("countTeachers", teacherService.findAll().size());
        model.addAttribute("countDepartments", departmentService.findAll().size());
        model.addAttribute("countStudents", studentService.findAll().size());
        model.addAttribute("countAssistants", assistantService.findAll().size());
        model.addAttribute("countAssignments", teachingAssignmentService.findAll().size());
        model.addAttribute("countEnrollments", enrollmentService.findAll().size());
        return "dashboard/index";
    }
}
