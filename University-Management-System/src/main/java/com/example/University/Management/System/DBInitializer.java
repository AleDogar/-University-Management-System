package com.example.University.Management.System;

import com.example.University.Management.System.model.*;
import com.example.University.Management.System.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements CommandLineRunner {

    private final UniversityRepository universityRepository;
    private final DepartmentRepository departmentRepository;
    private final RoomRepository roomRepository;
    private final TeacherRepository teacherRepository;
    private final AssistantRepository assistantRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final TeachingAssignmentRepository teachingAssignmentRepository;

    public DBInitializer(
            UniversityRepository universityRepository,
            DepartmentRepository departmentRepository,
            RoomRepository roomRepository,
            TeacherRepository teacherRepository,
            AssistantRepository assistantRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            TeachingAssignmentRepository teachingAssignmentRepository
    ) {
        this.universityRepository = universityRepository;
        this.departmentRepository = departmentRepository;
        this.roomRepository = roomRepository;
        this.teacherRepository = teacherRepository;
        this.assistantRepository = assistantRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.teachingAssignmentRepository = teachingAssignmentRepository;
    }

    @Override
    public void run(String... args) {
        // Verifică dacă baza de date este deja populată
        if (isDatabaseAlreadyPopulated()) {
            System.out.println("✓ Baza de date deja populată");
            return;
        }

        System.out.println("⏳ Se populează baza de date cu date inițiale...");

        // Populează doar dacă baza de date este goală
        populateDatabase();

        System.out.println("✅ Baza de date populată cu succes!");
    }

    private boolean isDatabaseAlreadyPopulated() {
        // Verifică dacă există cel puțin un student în baza de date
        return !studentRepository.findAll().isEmpty();
    }

    private void populateDatabase() {
        // -----------------------
        // UNIVERSITIES
        // -----------------------
        universityRepository.create(new University("U1", "University 1", "City 1"));
        universityRepository.create(new University("U2", "University 2", "City 2"));
        universityRepository.create(new University("U3", "University 3", "City 3"));
        universityRepository.create(new University("U4", "University 4", "City 4"));
        universityRepository.create(new University("U5", "University 5", "City 5"));
        universityRepository.create(new University("U6", "University 6", "City 6"));
        universityRepository.create(new University("U7", "University 7", "City 7"));
        universityRepository.create(new University("U8", "University 8", "City 8"));
        universityRepository.create(new University("U9", "University 9", "City 9"));
        universityRepository.create(new University("U10", "University 10", "City 10"));

        // -----------------------
        // DEPARTMENTS
        // -----------------------
        departmentRepository.create(new Department("D1", "Department 1", "071000001"));
        departmentRepository.create(new Department("D2", "Department 2", "071000002"));
        departmentRepository.create(new Department("D3", "Department 3", "071000003"));
        departmentRepository.create(new Department("D4", "Department 4", "071000004"));
        departmentRepository.create(new Department("D5", "Department 5", "071000005"));
        departmentRepository.create(new Department("D6", "Department 6", "071000006"));
        departmentRepository.create(new Department("D7", "Department 7", "071000007"));
        departmentRepository.create(new Department("D8", "Department 8", "071000008"));
        departmentRepository.create(new Department("D9", "Department 9", "071000009"));
        departmentRepository.create(new Department("D10", "Department 10", "071000010"));

        // -----------------------
        // ROOMS
        // -----------------------
        roomRepository.create(new Room("R1", "Room 1", 30));
        roomRepository.create(new Room("R2", "Room 2", 32));
        roomRepository.create(new Room("R3", "Room 3", 34));
        roomRepository.create(new Room("R4", "Room 4", 36));
        roomRepository.create(new Room("R5", "Room 5", 38));
        roomRepository.create(new Room("R6", "Room 6", 40));
        roomRepository.create(new Room("R7", "Room 7", 42));
        roomRepository.create(new Room("R8", "Room 8", 44));
        roomRepository.create(new Room("R9", "Room 9", 46));
        roomRepository.create(new Room("R10", "Room 10", 48));

        // -----------------------
        // TEACHERS
        // -----------------------
        teacherRepository.create(new Teacher("T1", "Teacher 1", "Professor", "D1", "t1@univ.com"));
        teacherRepository.create(new Teacher("T2", "Teacher 2", "Lecturer", "D2", "t2@univ.com"));
        teacherRepository.create(new Teacher("T3", "Teacher 3", "Professor", "D3", "t3@univ.com"));
        teacherRepository.create(new Teacher("T4", "Teacher 4", "Lecturer", "D4", "t4@univ.com"));
        teacherRepository.create(new Teacher("T5", "Teacher 5", "Professor", "D5", "t5@univ.com"));
        teacherRepository.create(new Teacher("T6", "Teacher 6", "Lecturer", "D6", "t6@univ.com"));
        teacherRepository.create(new Teacher("T7", "Teacher 7", "Professor", "D7", "t7@univ.com"));
        teacherRepository.create(new Teacher("T8", "Teacher 8", "Lecturer", "D8", "t8@univ.com"));
        teacherRepository.create(new Teacher("T9", "Teacher 9", "Professor", "D9", "t9@univ.com"));
        teacherRepository.create(new Teacher("T10", "Teacher 10", "Lecturer", "D10", "t10@univ.com"));

        // -----------------------
        // ASSISTANTS
        // -----------------------
        assistantRepository.create(new Assistant("A1", "Assistant 1", ClassRole.LAB, "a1@univ.com"));
        assistantRepository.create(new Assistant("A2", "Assistant 2", ClassRole.TA, "a2@univ.com"));
        assistantRepository.create(new Assistant("A3", "Assistant 3", ClassRole.LAB, "a3@univ.com"));
        assistantRepository.create(new Assistant("A4", "Assistant 4", ClassRole.TA, "a4@univ.com"));
        assistantRepository.create(new Assistant("A5", "Assistant 5", ClassRole.LAB, "a5@univ.com"));
        assistantRepository.create(new Assistant("A6", "Assistant 6", ClassRole.TA, "a6@univ.com"));
        assistantRepository.create(new Assistant("A7", "Assistant 7", ClassRole.LAB, "a7@univ.com"));
        assistantRepository.create(new Assistant("A8", "Assistant 8", ClassRole.TA, "a8@univ.com"));
        assistantRepository.create(new Assistant("A9", "Assistant 9", ClassRole.LAB, "a9@univ.com"));
        assistantRepository.create(new Assistant("A10", "Assistant 10", ClassRole.TA, "a10@univ.com"));

        // -----------------------
        // COURSES
        // -----------------------
        courseRepository.create(new Course("C1", "Course 1", 3, "D1", "R1"));
        courseRepository.create(new Course("C2", "Course 2", 3, "D2", "R2"));
        courseRepository.create(new Course("C3", "Course 3", 3, "D3", "R3"));
        courseRepository.create(new Course("C4", "Course 4", 3, "D4", "R4"));
        courseRepository.create(new Course("C5", "Course 5", 3, "D5", "R5"));
        courseRepository.create(new Course("C6", "Course 6", 3, "D6", "R6"));
        courseRepository.create(new Course("C7", "Course 7", 3, "D7", "R7"));
        courseRepository.create(new Course("C8", "Course 8", 3, "D8", "R8"));
        courseRepository.create(new Course("C9", "Course 9", 3, "D9", "R9"));
        courseRepository.create(new Course("C10", "Course 10", 3, "D10", "R10"));

        // -----------------------
        // STUDENTS
        // -----------------------
        studentRepository.create(new Student("S1", "Student 1", "s1@mail.com"));
        studentRepository.create(new Student("S2", "Student 2", "s2@mail.com"));
        studentRepository.create(new Student("S3", "Student 3", "s3@mail.com"));
        studentRepository.create(new Student("S4", "Student 4", "s4@mail.com"));
        studentRepository.create(new Student("S5", "Student 5", "s5@mail.com"));
        studentRepository.create(new Student("S6", "Student 6", "s6@mail.com"));
        studentRepository.create(new Student("S7", "Student 7", "s7@mail.com"));
        studentRepository.create(new Student("S8", "Student 8", "s8@mail.com"));
        studentRepository.create(new Student("S9", "Student 9", "s9@mail.com"));
        studentRepository.create(new Student("S10", "Student 10", "s10@mail.com"));

// -----------------------
// ENROLLMENTS
// -----------------------
        enrollmentRepository.create(new Enrollment("E1", "S1", "C1", ClassGrade.A));
        enrollmentRepository.create(new Enrollment("E2", "S2", "C2", ClassGrade.B));
        enrollmentRepository.create(new Enrollment("E3", "S3", "C3", ClassGrade.C));
        enrollmentRepository.create(new Enrollment("E4", "S4", "C4", ClassGrade.A));
        enrollmentRepository.create(new Enrollment("E5", "S5", "C5", ClassGrade.B));
        enrollmentRepository.create(new Enrollment("E6", "S6", "C6", ClassGrade.C));
        enrollmentRepository.create(new Enrollment("E7", "S7", "C7", ClassGrade.A));
        enrollmentRepository.create(new Enrollment("E8", "S8", "C8", ClassGrade.B));
        enrollmentRepository.create(new Enrollment("E9", "S9", "C9", ClassGrade.C));
        enrollmentRepository.create(new Enrollment("E10", "S10", "C10", ClassGrade.A));
// -----------------------
// TEACHING ASSIGNMENTS - CORECTAT
// -----------------------


// Cursuri COURSE cu profesori
        teachingAssignmentRepository.create(new TeachingAssignment("TA1", ClassType.COURSE, "C1", "T1"));
        teachingAssignmentRepository.create(new TeachingAssignment("TA3", ClassType.COURSE, "C3", "T3"));
        teachingAssignmentRepository.create(new TeachingAssignment("TA5", ClassType.COURSE, "C5", "T5"));
        teachingAssignmentRepository.create(new TeachingAssignment("TA7", ClassType.COURSE, "C7", "T7"));
        teachingAssignmentRepository.create(new TeachingAssignment("TA9", ClassType.COURSE, "C9", "T9"));

    }
}