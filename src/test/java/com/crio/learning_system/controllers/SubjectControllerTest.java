package com.crio.learning_system.controllers;

import com.crio.learning_system.config.AppSecurityConfig;
import com.crio.learning_system.dtos.CreateSubjectDTO;
import com.crio.learning_system.entities.Subject;
import com.crio.learning_system.services.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SubjectController.class)
@Import(AppSecurityConfig.class)
public class SubjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubjectService subjectService;

    @Test
    void testGetAllSubject() throws Exception {
        // Given
        Subject subject1 = new Subject();
        subject1.setId("1");
        subject1.setSubjectName("Maths");

        Subject subject2 = new Subject();
        subject2.setId("2");
        subject2.setSubjectName("Science");

        List<Subject> subjects = Arrays.asList(subject1, subject2);

        when(subjectService.getAllSubject()).thenReturn(subjects);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/subject")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subjectName").value("Maths"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].subjectName").value("Science"));

        verify(subjectService, times(1)).getAllSubject();
    }

    @Test
    void testGetSubjectById() throws Exception {
        // Given
        String subjectId = "1";
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject.setSubjectName("Maths");

        when(subjectService.getSubjectById(subjectId)).thenReturn(subject);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/subject/{id}", subjectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(subjectId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName").value("Maths"));

        verify(subjectService, times(1)).getSubjectById(subjectId);
    }

    @Test
    void testCreateSubject() throws Exception {
        // Given
        CreateSubjectDTO createSubjectDTO = new CreateSubjectDTO();
        createSubjectDTO.setSubjectName("Maths");

        Subject createdSubject = new Subject();
        createdSubject.setId("1");
        createdSubject.setSubjectName("Maths");

        when(subjectService.createSubject(createSubjectDTO)).thenReturn(createdSubject);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/subject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSubjectDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subjectName").value("Maths"));

        verify(subjectService, times(1)).createSubject(createSubjectDTO);
    }

    @Test
    void testDeleteSubject() throws Exception {
        // Given
        String subjectId = "1";

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/subject/{id}", subjectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(subjectService, times(1)).deleteSubject(subjectId);
    }
}
