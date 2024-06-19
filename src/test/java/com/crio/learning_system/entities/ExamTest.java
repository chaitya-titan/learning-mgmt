package com.crio.learning_system.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExamTest {
    private Exam exam;

    @BeforeEach
    void setUp() {
        exam = new Exam();
    }

    @Test
    void testSubjectNameField() {
        // Create a Subject instance
        Subject subject = new Subject();
        subject.setId("1");
        subject.setSubjectName("Math");

        // Set the subjectName field
        exam.setSubjectName(subject);

        // Verify the subjectName field
        assertEquals(subject, exam.getSubjectName(), "Subject name should match");
    }

    @Test
    void testStudentsField() {
        // Create a Set of Student instances
        Set<Student> students = new HashSet<>();
        Student student1 = new Student();
        student1.setId("1");
        student1.setStudentName("John");
        students.add(student1);

        Student student2 = new Student();
        student2.setId("2");
        student2.setStudentName("Alice");
        students.add(student2);

        // Set the students field
        exam.setStudents(students);

        // Verify the students field
        assertEquals(students, exam.getStudents(), "Students set should match");
    }
}
