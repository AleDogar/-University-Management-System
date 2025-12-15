package com.example.University.Management.System;

import com.example.University.Management.System.model.*;
import com.example.University.Management.System.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer {

    private final UniversityService universityService;
    private final DepartmentService departmentService;
    private final RoomService roomService;
    private final TeacherService teacherService;
    private final AssistantService assistantService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;
    private final TeachingAssignmentService teachingAssignmentService;

    public DBInitializer(UniversityService universityService,
                         DepartmentService departmentService,
                         RoomService roomService,
                         TeacherService teacherService,
                         AssistantService assistantService,
                         CourseService courseService,
                         StudentService studentService,
                         EnrollmentService enrollmentService,
                         TeachingAssignmentService teachingAssignmentService) {
        this.universityService = universityService;
        this.departmentService = departmentService;
        this.roomService = roomService;
        this.teacherService = teacherService;
        this.assistantService = assistantService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.teachingAssignmentService = teachingAssignmentService;
    }

    @PostConstruct
    public void init() {
        System.out.println("==========================================");
        System.out.println("=== DBInitializer PORNIT ===");
        System.out.println("==========================================");

        // Verifică dacă există deja date
        if (!universityService.findAll().isEmpty()) {
            System.out.println("✓ Datele există deja în baza de date!");
            System.out.println("Dacă vrei să repopulezi, șterge datele din MySQL:");
            System.out.println("TRUNCATE TABLE universities (și toate celelalte)");
            System.out.println("==========================================");
            return;
        }

        System.out.println("Baza de date este goală. Începe popularea...");
        System.out.println("==========================================");

        try {
            // 1. UNIVERSITIES
            System.out.println("Creare universități...");
            universityService.create(new University("U1", "Universitatea Tehnica", "Cluj"));
            universityService.create(new University("U2", "University of Bucharest", "Bucuresti"));
            universityService.create(new University("U3", "Universitatea din Iasi", "Iasi"));
            universityService.create(new University("U4", "Universitatea din Timisoara", "Timisoara"));
            universityService.create(new University("U5", "Universitatea din Craiova", "Craiova"));
            universityService.create(new University("U6", "Universitatea din Oradea", "Oradea"));
            universityService.create(new University("U7", "Universitatea din Brasov", "Brasov"));
            universityService.create(new University("U8", "Universitatea din Sibiu", "Sibiu"));
            universityService.create(new University("U9", "Universitatea din Constanta", "Constanta"));
            universityService.create(new University("U10", "Universitatea din Galati", "Galati"));
            System.out.println("✓ 10 universități create");

            // 2. DEPARTMENTS
            System.out.println("Creare departamente...");
            departmentService.create(new Department("D1", "Informatica", "0740123456"));
            departmentService.create(new Department("D2", "Matematica", "0740234567"));
            departmentService.create(new Department("D3", "Fizica", "0740345678"));
            departmentService.create(new Department("D4", "Chimie", "0740456789"));
            departmentService.create(new Department("D5", "Biologie", "0740567890"));
            departmentService.create(new Department("D6", "Istorie", "0740678901"));
            departmentService.create(new Department("D7", "Literatura", "0740789012"));
            departmentService.create(new Department("D8", "Economie", "0740890123"));
            departmentService.create(new Department("D9", "Drept", "0740901234"));
            departmentService.create(new Department("D10", "Medicina", "0741012345"));
            System.out.println("✓ 10 departamente create");

            // 3. ROOMS
            System.out.println("Creare săli...");
            roomService.create(new Room("R1", "Sala 101", 30));
            roomService.create(new Room("R2", "Sala 102", 25));
            roomService.create(new Room("R3", "Sala 201", 40));
            roomService.create(new Room("R4", "Sala 202", 35));
            roomService.create(new Room("R5", "Sala 301", 50));
            roomService.create(new Room("R6", "Sala 302", 45));
            roomService.create(new Room("R7", "Amfiteatru A", 100));
            roomService.create(new Room("R8", "Amfiteatru B", 120));
            roomService.create(new Room("R9", "Laborator 1", 20));
            roomService.create(new Room("R10", "Laborator 2", 15));
            System.out.println("✓ 10 săli create");

            // 4. TEACHERS
            System.out.println("Creare profesori...");
            teacherService.create(new Teacher("T1", "Profesor Popescu", "Lecturer", "D1", "popescu@univ.com"));
            teacherService.create(new Teacher("T2", "Profesor Ionescu", "Senior Lecturer", "D2", "ionescu@univ.com"));
            teacherService.create(new Teacher("T3", "Profesor Georgescu", "Professor", "D3", "georgescu@univ.com"));
            teacherService.create(new Teacher("T4", "Profesor Marin", "Associate Professor", "D4", "marin@univ.com"));
            teacherService.create(new Teacher("T5", "Profesor Stan", "Assistant Professor", "D5", "stan@univ.com"));
            teacherService.create(new Teacher("T6", "Profesor Moldovan", "Lecturer", "D6", "moldovan@univ.com"));
            teacherService.create(new Teacher("T7", "Profesor Radu", "Senior Lecturer", "D7", "radu@univ.com"));
            teacherService.create(new Teacher("T8", "Profesor Gheorghe", "Professor", "D8", "gheorghe@univ.com"));
            teacherService.create(new Teacher("T9", "Profesor Dumitru", "Associate Professor", "D9", "dumitru@univ.com"));
            teacherService.create(new Teacher("T10", "Profesor Vasile", "Assistant Professor", "D10", "vasile@univ.com"));
            System.out.println("✓ 10 profesori creați");

            // 5. ASSISTANTS
            System.out.println("Creare asistenți...");
            assistantService.create(new Assistant("A1", "Asistent Maria", ClassRole.LAB, "maria@univ.com"));
            assistantService.create(new Assistant("A2", "Asistent Ion", ClassRole.TA, "ion@univ.com"));
            assistantService.create(new Assistant("A3", "Asistent Ana", ClassRole.LAB, "ana@univ.com"));
            assistantService.create(new Assistant("A4", "Asistent Mihai", ClassRole.TA, "mihai@univ.com"));
            assistantService.create(new Assistant("A5", "Asistent Elena", ClassRole.LAB, "elena@univ.com"));
            assistantService.create(new Assistant("A6", "Asistent Cristian", ClassRole.TA, "cristian@univ.com"));
            assistantService.create(new Assistant("A7", "Asistent Andreea", ClassRole.LAB, "andreea@univ.com"));
            assistantService.create(new Assistant("A8", "Asistent Robert", ClassRole.TA, "robert@univ.com"));
            assistantService.create(new Assistant("A9", "Asistent Diana", ClassRole.LAB, "diana@univ.com"));
            assistantService.create(new Assistant("A10", "Asistent Vlad", ClassRole.TA, "vlad@univ.com"));
            System.out.println("✓ 10 asistenți creați");

            // 6. COURSES
            System.out.println("Creare cursuri...");
            courseService.create(new Course("C1", "Programare Java", 5, "D1", "R1"));
            courseService.create(new Course("C2", "Algebra", 4, "D2", "R2"));
            courseService.create(new Course("C3", "Fizica Generala", 6, "D3", "R3"));
            courseService.create(new Course("C4", "Chimie Organica", 5, "D4", "R4"));
            courseService.create(new Course("C5", "Biologie Moleculara", 4, "D5", "R5"));
            courseService.create(new Course("C6", "Istoria Romaniei", 3, "D6", "R6"));
            courseService.create(new Course("C7", "Literatura Romana", 4, "D7", "R7"));
            courseService.create(new Course("C8", "Economie Politica", 5, "D8", "R8"));
            courseService.create(new Course("C9", "Drept Civil", 6, "D9", "R9"));
            courseService.create(new Course("C10", "Anatomie", 5, "D10", "R10"));
            System.out.println("✓ 10 cursuri create");

            // 7. TEACHING ASSIGNMENTS
            System.out.println("Creare asignări...");
            Course c1 = courseService.findById("C1");
            Course c2 = courseService.findById("C2");
            Course c3 = courseService.findById("C3");
            Course c4 = courseService.findById("C4");
            Course c5 = courseService.findById("C5");
            Course c6 = courseService.findById("C6");
            Course c7 = courseService.findById("C7");
            Course c8 = courseService.findById("C8");
            Course c9 = courseService.findById("C9");
            Course c10 = courseService.findById("C10");

            teachingAssignmentService.create(new TeachingAssignment("TA1", ClassType.COURSE, c1, "T1"));
            teachingAssignmentService.create(new TeachingAssignment("TA2", ClassType.LAB, c1, "T2"));
            teachingAssignmentService.create(new TeachingAssignment("TA3", ClassType.SEMINARY, c2, "T3"));
            teachingAssignmentService.create(new TeachingAssignment("TA4", ClassType.COURSE, c3, "T4"));
            teachingAssignmentService.create(new TeachingAssignment("TA5", ClassType.LAB, c4, "T5"));
            teachingAssignmentService.create(new TeachingAssignment("TA6", ClassType.SEMINARY, c5, "T6"));
            teachingAssignmentService.create(new TeachingAssignment("TA7", ClassType.COURSE, c6, "T7"));
            teachingAssignmentService.create(new TeachingAssignment("TA8", ClassType.LAB, c7, "T8"));
            teachingAssignmentService.create(new TeachingAssignment("TA9", ClassType.SEMINARY, c8, "T9"));
            teachingAssignmentService.create(new TeachingAssignment("TA10", ClassType.COURSE, c9, "T10"));
            System.out.println("✓ 10 asignări create");

            // 8. STUDENTS
            System.out.println("Creare studenți...");
            studentService.create(new Student("S1", "Student Ana", "ana.student@univ.com"));
            studentService.create(new Student("S2", "Student Mihai", "mihai.student@univ.com"));
            studentService.create(new Student("S3", "Student Ion", "ion.student@univ.com"));
            studentService.create(new Student("S4", "Student Maria", "maria.student@univ.com"));
            studentService.create(new Student("S5", "Student Andrei", "andrei.student@univ.com"));
            studentService.create(new Student("S6", "Student Elena", "elena.student@univ.com"));
            studentService.create(new Student("S7", "Student Cristian", "cristian.student@univ.com"));
            studentService.create(new Student("S8", "Student Alexandra", "alexandra.student@univ.com"));
            studentService.create(new Student("S9", "Student George", "george.student@univ.com"));
            studentService.create(new Student("S10", "Student Raluca", "raluca.student@univ.com"));
            System.out.println("✓ 10 studenți creați");

            // 9. ENROLLMENTS
            System.out.println("Creare înscrieri...");
            enrollmentService.create(new Enrollment("E1", "S1", "C1", ClassGrade.A));
            enrollmentService.create(new Enrollment("E2", "S2", "C2", ClassGrade.B));
            enrollmentService.create(new Enrollment("E3", "S3", "C3", ClassGrade.C));
            enrollmentService.create(new Enrollment("E4", "S4", "C4", ClassGrade.A));
            enrollmentService.create(new Enrollment("E5", "S5", "C5", ClassGrade.B));
            enrollmentService.create(new Enrollment("E6", "S6", "C6", ClassGrade.C));
            enrollmentService.create(new Enrollment("E7", "S7", "C7", ClassGrade.A));
            enrollmentService.create(new Enrollment("E8", "S8", "C8", ClassGrade.B));
            enrollmentService.create(new Enrollment("E9", "S9", "C9", ClassGrade.C));
            enrollmentService.create(new Enrollment("E10", "S10", "C10", ClassGrade.A));
            System.out.println("✓ 10 înscrieri create");

            System.out.println("==========================================");
            System.out.println("=== ✓ INITIALIZARE COMPLETĂ CU SUCCES! ===");
            System.out.println("==========================================");

        } catch (Exception e) {
            System.err.println("==========================================");
            System.err.println("✗ EROARE la initializare: " + e.getMessage());
            System.err.println("==========================================");
            e.printStackTrace();
        }
    }
}