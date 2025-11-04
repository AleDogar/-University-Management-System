package com.example.University.Management.System;

import com.example.University.Management.System.model.*;
import com.example.University.Management.System.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class UniversityManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(
            UniversityService universityService,
            DepartmentService departmentService,
            CourseService courseService,
            RoomService roomService,
            StudentService studentService,
            TeacherService teacherService,
            AssistantService assistantService,
            EnrollmentService enrollmentService,
            TeachingAssignmentService teachingAssignmentService
    ) {
        return args -> {

            // ---------- UNIVERSITIES ----------
            University utcn = new University("U1", "Universitatea Tehnică din Cluj-Napoca", "Cluj-Napoca");
            University ubb = new University("U2", "Universitatea Babeș-Bolyai", "Cluj-Napoca");
            universityService.addUniversity(utcn);
            universityService.addUniversity(ubb);

            // ---------- ROOMS ----------
            Room r1 = new Room("R1", 120, "A101", List.of());
            Room r2 = new Room("R2", 30, "B202", List.of());
            Room r3 = new Room("R3", 200, "C303", List.of());
            roomService.addRoom(r1);
            roomService.addRoom(r2);
            roomService.addRoom(r3);

            // ---------- DEPARTMENTS ----------
            Department d1 = new Department("D1", "Informatica", List.of(), List.of(), "0264-123456");
            Department d2 = new Department("D2", "Matematica", List.of(), List.of(), "0264-654321");
            Department d3 = new Department("D3", "Inginerie", List.of(), List.of(), "0264-987654");
            departmentService.addDepartment(d1);
            departmentService.addDepartment(d2);
            departmentService.addDepartment(d3);

            // ---------- COURSES ----------
            Course c1 = new Course("C1", "Programare Java", 6, "D1", "R1", List.of(), List.of());
            Course c2 = new Course("C2", "Algoritmi și Structuri de Date", 5, "D1", "R2", List.of(), List.of());
            Course c3 = new Course("C3", "Analiză Matematică", 4, "D2", "R3", List.of(), List.of());
            Course c4 = new Course("C4", "Baze de Date", 5, "D3", "R2", List.of(), List.of());
            courseService.addCourse(c1);
            courseService.addCourse(c2);
            courseService.addCourse(c3);
            courseService.addCourse(c4);

            // ---------- ENROLLMENTS ----------
            Enrollment e1 = new Enrollment("E1", "C1", ClassGrade.A);
            Enrollment e2 = new Enrollment("E2", "C2", ClassGrade.B);
            Enrollment e3 = new Enrollment("E3", "C3", ClassGrade.C);
            Enrollment e4 = new Enrollment("E4", "C4", ClassGrade.A);
            enrollmentService.addEnrollment(e1);
            enrollmentService.addEnrollment(e2);
            enrollmentService.addEnrollment(e3);
            enrollmentService.addEnrollment(e4);

            // ---------- STUDENTS ----------
            Student s1 = new Student(List.of(e1, e2), "Dogar Alesia", "S1", "dogar.alesia@student.utcluj.ro", 10);
            Student s2 = new Student(List.of(e3), "Dreghiciu Mihai", "S2", "mihai.dreghiciu@student.ubbcluj.ro", 10);
            Student s3 = new Student(List.of(e4), "Muresan Laura", "S3", "laura.muresan@student.utcluj.ro", 10);
            studentService.addStudent(s1);
            studentService.addStudent(s2);
            studentService.addStudent(s3);

            // ---------- TEACHING ASSIGNMENTS ----------
            TeachingAssignment ta1 = new TeachingAssignment("TA1", ClassType.Course, "C1", "T1");
            TeachingAssignment ta2 = new TeachingAssignment("TA2", ClassType.Lab, "C1", "A1");
            TeachingAssignment ta3 = new TeachingAssignment("TA3", ClassType.Course, "C2", "T2");
            TeachingAssignment ta4 = new TeachingAssignment("TA4", ClassType.Seminary, "C3", "A2");
            teachingAssignmentService.addTeachingAssignment(ta1);
            teachingAssignmentService.addTeachingAssignment(ta2);
            teachingAssignmentService.addTeachingAssignment(ta3);
            teachingAssignmentService.addTeachingAssignment(ta4);

            // ---------- TEACHERS ----------
            Teacher t1 = new Teacher(List.of(ta1), "Rusu Catalin", "T1");
            t1.setTitle("Profesor universitar");
            t1.setDepartmentID("D1");
            t1.setEmail("rusu.catalin@utcluj.ro");

            Teacher t2 = new Teacher(List.of(ta3), "Dicu Madalina", "T2");
            t2.setTitle("Profesoara universitara");
            t2.setDepartmentID("D2");
            t2.setEmail("madalina.dicu@ubbcluj.ro");

            teacherService.addTeacher(t1);
            teacherService.addTeacher(t2);

            // ---------- ASSISTANTS ----------
            Assistant a1 = new Assistant(ClassRole.Lab, "Ioana Mureșan", "A1", List.of(ta2));
            Assistant a2 = new Assistant(ClassRole.TA, "Mihai Dumitru", "A2", List.of(ta4));
            assistantService.addAssistant(a1);
            assistantService.addAssistant(a2);
        };
    }
}
