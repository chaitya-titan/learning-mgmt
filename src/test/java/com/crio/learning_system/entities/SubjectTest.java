package com.crio.learning_system.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SubjectTest {
    private Subject subject;

    @BeforeEach
    void setUp() {
        subject = new Subject();
    }

    @Test
    void testSubjectNameField() {
        // Set the subjectName field
        String subjectName = "Mathematics";
        subject.setSubjectName(subjectName);

        // Verify the subjectName field
        assertEquals(subjectName, subject.getSubjectName(), "Subject name should match");
    }

    @Test
    void testRegisteredStudentsField() {
        // Create a Set of Student instances
        Set<Student> registeredStudents = new HashSet<>();
        Student student1 = new Student();
        student1.setId("1");
        student1.setStudentName("John");
        registeredStudents.add(student1);

        Student student2 = new Student();
        student2.setId("2");
        student2.setStudentName("Alice");
        registeredStudents.add(student2);

        // Set the registeredStudents field
        subject.setRegisteredStudents(registeredStudents);

        // Verify the registeredStudents field
        assertEquals(registeredStudents, subject.getRegisteredStudents(), "Registered students set should match");
    }
}
