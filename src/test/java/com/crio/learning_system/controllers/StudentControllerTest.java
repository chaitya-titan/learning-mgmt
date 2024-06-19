package com.crio.learning_system.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.crio.learning_system.config.AppSecurityConfig;
import com.crio.learning_system.dtos.RegisterStudentDTO;
import com.crio.learning_system.entities.Student;
import com.crio.learning_system.services.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
@Import(AppSecurityConfig.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId("1");
        student.setStudentName("John Doe");
    }

    @Test
    void testRegisterStudent() throws Exception {
        RegisterStudentDTO registerStudentDTO = new RegisterStudentDTO();
        registerStudentDTO.setStudentName("John Doe");

        when(studentService.registerStudent(registerStudentDTO)).thenReturn(student);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"studentName\":\"John Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.studentName").value(student.getStudentName()));
    }

    @Test
    void testGetAllStudent() throws Exception {
        when(studentService.getAllStudent()).thenReturn(Collections.singletonList(student));

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(student.getId()))
                .andExpect(jsonPath("$[0].studentName").value(student.getStudentName()));
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudentById("1")).thenReturn(student);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.studentName").value(student.getStudentName()));
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully"));

        verify(studentService, times(1)).deleteStudent("1");
    }

    // Additional test cases for registerStudentToSubject and enrollStudentToExam endpoints can be added similarly
}