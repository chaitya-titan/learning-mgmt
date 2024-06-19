package com.crio.learning_system.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
    }

    @Test
    void testStudentNameField() {
        // Set the studentName field
        String studentName = "John Doe";
        student.setStudentName(studentName);

        // Verify the studentName field
        assertEquals(studentName, student.getStudentName(), "Student name should match");
    }

    @Test
    void testSubjectsField() {
        // Create a Set of Subject instances
        Set<Subject> subjects = new HashSet<>();
        Subject subject1 = new Subject();
        subject1.setId("1");
        subject1.setSubjectName("Math");
        subjects.add(subject1);

        Subject subject2 = new Subject();
        subject2.setId("2");
        subject2.setSubjectName("Science");
        subjects.add(subject2);

        // Set the subjects field
        student.setSubjects(subjects);

        // Verify the subjects field
        assertEquals(subjects, student.getSubjects(), "Subjects set should match");
    }

    @Test
    void testExamsField() {
        // Create a Set of Exam instances
        Set<Exam> exams = new HashSet<>();
        Exam exam1 = new Exam();
        exam1.setId("1");
        exam1.setSubjectName(new Subject());
        exams.add(exam1);

        Exam exam2 = new Exam();
        exam2.setId("2");
        exam2.setSubjectName(new Subject());
        exams.add(exam2);

        // Set the exams field
        student.setExams(exams);

        // Verify the exams field
        assertEquals(exams, student.getExams(), "Exams set should match");
    }
}
