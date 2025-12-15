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
        System.out.println("Initializare baza de date...");

        try {
            // 1. Creăm 10 universități
            University uni1 = new University("U1", "Universitatea Tehnica", "Cluj");
            University uni2 = new University("U2", "University of Bucharest", "Bucuresti");
            University uni3 = new University("U3", "Universitatea din Iasi", "Iasi");
            University uni4 = new University("U4", "Universitatea din Timisoara", "Timisoara");
            University uni5 = new University("U5", "Universitatea din Craiova", "Craiova");
            University uni6 = new University("U6", "Universitatea din Oradea", "Oradea");
            University uni7 = new University("U7", "Universitatea din Brasov", "Brasov");
            University uni8 = new University("U8", "Universitatea din Sibiu", "Sibiu");
            University uni9 = new University("U9", "Universitatea din Constanta", "Constanta");
            University uni10 = new University("U10", "Universitatea din Galati", "Galati");

            universityService.create(uni1);
            universityService.create(uni2);
            universityService.create(uni3);
            universityService.create(uni4);
            universityService.create(uni5);
            universityService.create(uni6);
            universityService.create(uni7);
            universityService.create(uni8);
            universityService.create(uni9);
            universityService.create(uni10);
            System.out.println("10 universități create");

            // 2. Creăm 10 departamente
            Department dep1 = new Department("D1", "Informatica", "07401001");
            Department dep2 = new Department("D2", "Matematica", "07401002");
            Department dep3 = new Department("D3", "Fizica", "07401003");
            Department dep4 = new Department("D4", "Chimie", "07401004");
            Department dep5 = new Department("D5", "Biologie", "07401005");
            Department dep6 = new Department("D6", "Istorie", "07401006");
            Department dep7 = new Department("D7", "Literatura", "07401007");
            Department dep8 = new Department("D8", "Economie", "07401008");
            Department dep9 = new Department("D9", "Drept", "07401009");
            Department dep10 = new Department("D10", "Medicina", "07401010");

            departmentService.create(dep1);
            departmentService.create(dep2);
            departmentService.create(dep3);
            departmentService.create(dep4);
            departmentService.create(dep5);
            departmentService.create(dep6);
            departmentService.create(dep7);
            departmentService.create(dep8);
            departmentService.create(dep9);
            departmentService.create(dep10);
            System.out.println("10 departamente create");

            // 3. Creăm 10 săli
            Room room1 = new Room("R1", "Sala 101", 30);
            Room room2 = new Room("R2", "Sala 102", 25);
            Room room3 = new Room("R3", "Sala 201", 40);
            Room room4 = new Room("R4", "Sala 202", 35);
            Room room5 = new Room("R5", "Sala 301", 50);
            Room room6 = new Room("R6", "Sala 302", 45);
            Room room7 = new Room("R7", "Amfiteatru A", 100);
            Room room8 = new Room("R8", "Amfiteatru B", 120);
            Room room9 = new Room("R9", "Laborator 1", 20);
            Room room10 = new Room("R10", "Laborator 2", 15);

            roomService.create(room1);
            roomService.create(room2);
            roomService.create(room3);
            roomService.create(room4);
            roomService.create(room5);
            roomService.create(room6);
            roomService.create(room7);
            roomService.create(room8);
            roomService.create(room9);
            roomService.create(room10);
            System.out.println("10 săli create");

            // 4. Creăm 10 profesori
            Teacher teacher1 = new Teacher("T1", "Profesor Popescu", "Lecturer", "D1", "popescu@univ.com");
            Teacher teacher2 = new Teacher("T2", "Profesor Ionescu", "Senior Lecturer", "D2", "ionescu@univ.com");
            Teacher teacher3 = new Teacher("T3", "Profesor Georgescu", "Professor", "D3", "georgescu@univ.com");
            Teacher teacher4 = new Teacher("T4", "Profesor Marin", "Associate Professor", "D4", "marin@univ.com");
            Teacher teacher5 = new Teacher("T5", "Profesor Stan", "Assistant Professor", "D5", "stan@univ.com");
            Teacher teacher6 = new Teacher("T6", "Profesor Moldovan", "Lecturer", "D6", "moldovan@univ.com");
            Teacher teacher7 = new Teacher("T7", "Profesor Radu", "Senior Lecturer", "D7", "radu@univ.com");
            Teacher teacher8 = new Teacher("T8", "Profesor Gheorghe", "Professor", "D8", "gheorghe@univ.com");
            Teacher teacher9 = new Teacher("T9", "Profesor Dumitru", "Associate Professor", "D9", "dumitru@univ.com");
            Teacher teacher10 = new Teacher("T10", "Profesor Vasile", "Assistant Professor", "D10", "vasile@univ.com");

            teacherService.create(teacher1);
            teacherService.create(teacher2);
            teacherService.create(teacher3);
            teacherService.create(teacher4);
            teacherService.create(teacher5);
            teacherService.create(teacher6);
            teacherService.create(teacher7);
            teacherService.create(teacher8);
            teacherService.create(teacher9);
            teacherService.create(teacher10);
            System.out.println("10 profesori create");

            // 5. Creăm 10 asistenți
            Assistant[] assistants = {
                    new Assistant("A1", "Asistent Maria", ClassRole.LAB, "maria@univ.com"),
                    new Assistant("A2", "Asistent Ion", ClassRole.TA, "ion@univ.com"),
                    new Assistant("A3", "Asistent Ana", ClassRole.LAB, "ana@univ.com"),
                    new Assistant("A4", "Asistent Mihai", ClassRole.TA, "mihai@univ.com"),
                    new Assistant("A5", "Asistent Elena", ClassRole.LAB, "elena@univ.com"),
                    new Assistant("A6", "Asistent Cristian", ClassRole.TA, "cristian@univ.com"),
                    new Assistant("A7", "Asistent Andreea", ClassRole.LAB, "andreea@univ.com"),
                    new Assistant("A8", "Asistent Robert", ClassRole.TA, "robert@univ.com"),
                    new Assistant("A9", "Asistent Diana", ClassRole.LAB, "diana@univ.com"),
                    new Assistant("A10", "Asistent Vlad", ClassRole.TA, "vlad@univ.com")
            };

            for (Assistant assistant : assistants) {
                if (assistantService.findById(assistant.getStaffID()) == null) {
                    assistantService.create(assistant);
                    System.out.println("Asistent creat: " + assistant.getStaffID());
                } else {
                    System.out.println("Asistentul cu ID " + assistant.getStaffID() + " există deja. Sar peste creare.");
                }
            }



            // 6. Creăm 10 cursuri
            Course course1 = new Course("C1", "Programare Java", 5, "D1", "R1");
            Course course2 = new Course("C2", "Algebra", 4, "D2", "R2");
            Course course3 = new Course("C3", "Fizica Generală", 6, "D3", "R3");
            Course course4 = new Course("C4", "Chimie Organică", 5, "D4", "R4");
            Course course5 = new Course("C5", "Biologie Moleculară", 4, "D5", "R5");
            Course course6 = new Course("C6", "Istoria României", 3, "D6", "R6");
            Course course7 = new Course("C7", "Literatura Română", 4, "D7", "R7");
            Course course8 = new Course("C8", "Economie Politică", 5, "D8", "R8");
            Course course9 = new Course("C9", "Drept Civil", 6, "D9", "R9");
            Course course10 = new Course("C10", "Anatomie", 5, "D10", "R10");

            courseService.create(course1);
            courseService.create(course2);
            courseService.create(course3);
            courseService.create(course4);
            courseService.create(course5);
            courseService.create(course6);
            courseService.create(course7);
            courseService.create(course8);
            courseService.create(course9);
            courseService.create(course10);
            System.out.println("10 cursuri create");

            // 7. Creăm 10 studenți
            Student student1 = new Student("S1", "Student Ana", "ana@univ.com");
            Student student2 = new Student("S2", "Student Mihai", "mihai@univ.com");
            Student student3 = new Student("S3", "Student Ion", "ion@univ.com");
            Student student4 = new Student("S4", "Student Maria", "maria@univ.com");
            Student student5 = new Student("S5", "Student Andrei", "andrei@univ.com");
            Student student6 = new Student("S6", "Student Elena", "elena@univ.com");
            Student student7 = new Student("S7", "Student Cristian", "cristian@univ.com");
            Student student8 = new Student("S8", "Student Alexandra", "alexandra@univ.com");
            Student student9 = new Student("S9", "Student George", "george@univ.com");
            Student student10 = new Student("S10", "Student Raluca", "raluca@univ.com");

            studentService.create(student1);
            studentService.create(student2);
            studentService.create(student3);
            studentService.create(student4);
            studentService.create(student5);
            studentService.create(student6);
            studentService.create(student7);
            studentService.create(student8);
            studentService.create(student9);
            studentService.create(student10);
            System.out.println("10 studenți create");

            // 8. Creăm 10 înscrieri
            Enrollment enrollment1 = new Enrollment("E1", "S1", "C1", ClassGrade.A);
            Enrollment enrollment2 = new Enrollment("E2", "S2", "C2", ClassGrade.B);
            Enrollment enrollment3 = new Enrollment("E3", "S3", "C3", ClassGrade.C);
            Enrollment enrollment4 = new Enrollment("E4", "S4", "C4", ClassGrade.A);
            Enrollment enrollment5 = new Enrollment("E5", "S5", "C5", ClassGrade.B);
            Enrollment enrollment6 = new Enrollment("E6", "S6", "C6", ClassGrade.C);
            Enrollment enrollment7 = new Enrollment("E7", "S7", "C7", ClassGrade.A);
            Enrollment enrollment8 = new Enrollment("E8", "S8", "C8", ClassGrade.B);
            Enrollment enrollment9 = new Enrollment("E9", "S9", "C9", ClassGrade.C);
            Enrollment enrollment10 = new Enrollment("E10", "S10", "C10", ClassGrade.A);

            enrollmentService.create(enrollment1);
            enrollmentService.create(enrollment2);
            enrollmentService.create(enrollment3);
            enrollmentService.create(enrollment4);
            enrollmentService.create(enrollment5);
            enrollmentService.create(enrollment6);
            enrollmentService.create(enrollment7);
            enrollmentService.create(enrollment8);
            enrollmentService.create(enrollment9);
            enrollmentService.create(enrollment10);
            System.out.println("10 înscrieri create");

// În metoda init() a DBInitializer, adaugă la sfârșit:

// --- Teaching Assignments (10 asignări) ---
            TeachingAssignment ta1 = new TeachingAssignment("TA1", ClassType.COURSE, "C1", "T1");
            TeachingAssignment ta2 = new TeachingAssignment("TA2", ClassType.SEMINARY, "C1", "T2");
            TeachingAssignment ta3 = new TeachingAssignment("TA3", ClassType.LAB, "C1", "A1");
            TeachingAssignment ta4 = new TeachingAssignment("TA4", ClassType.COURSE, "C2", "T3");
            TeachingAssignment ta5 = new TeachingAssignment("TA5", ClassType.SEMINARY, "C2", "T4");
            TeachingAssignment ta6 = new TeachingAssignment("TA6", ClassType.LAB, "C2", "A2");
            TeachingAssignment ta7 = new TeachingAssignment("TA7", ClassType.COURSE, "C3", "T5");
            TeachingAssignment ta8 = new TeachingAssignment("TA8", ClassType.SEMINARY, "C3", "T6");
            TeachingAssignment ta9 = new TeachingAssignment("TA9", ClassType.LAB, "C3", "A3");
            TeachingAssignment ta10 = new TeachingAssignment("TA10", ClassType.COURSE, "C4", "T7");

            teachingAssignmentService.create(ta1);
            teachingAssignmentService.create(ta2);
            teachingAssignmentService.create(ta3);
            teachingAssignmentService.create(ta4);
            teachingAssignmentService.create(ta5);
            teachingAssignmentService.create(ta6);
            teachingAssignmentService.create(ta7);
            teachingAssignmentService.create(ta8);
            teachingAssignmentService.create(ta9);
            teachingAssignmentService.create(ta10);
            System.out.println("10 teaching assignments create");

            System.out.println("Initializare completă cu succes!");

        } catch (Exception e) {
            System.err.println("Eroare la initializare: " + e.getMessage());
            // Nu arunca excepția mai departe pentru a permite pornirea aplicației
            e.printStackTrace();
        }
    }
}
