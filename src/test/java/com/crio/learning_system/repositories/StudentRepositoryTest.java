package com.crio.learning_system.repositories;

import com.crio.learning_system.entities.Student;
import com.crio.learning_system.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class StudentRepositoryTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Student student = new Student();
        student.setId("1");
        student.setStudentName("test");
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById("1");
        assertTrue(foundStudent.getStudentName().equals("test"));
        assertEquals("1", foundStudent.getId());
    }
}
