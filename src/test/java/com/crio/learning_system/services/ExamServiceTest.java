package com.crio.learning_system.services;

import com.crio.learning_system.dtos.RequestCreateExamDTO;
import com.crio.learning_system.entities.Exam;
import com.crio.learning_system.entities.Subject;
import com.crio.learning_system.exceptions.ExamNotFoundException;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import com.crio.learning_system.repositories.ExamRepository;
import com.crio.learning_system.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {
    @Mock
    private ExamRepository examRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ExamService examService;

    private RequestCreateExamDTO requestCreateExamDTO;
    private Exam exam;
    private Subject subject;

    @BeforeEach
    void setup(){
        requestCreateExamDTO = new RequestCreateExamDTO();
        requestCreateExamDTO.setSubjectId("subject-id");

        subject = new Subject();
        subject.setId("subject-id");
        subject.setSubjectName("Sample Subject");

        exam = new Exam();
        exam.setId("exam-id");
        exam.setSubjectName(subject);
    }

    @Test
    void testCreateExam() {
        when(subjectRepository.findById(requestCreateExamDTO.getSubjectId())).thenReturn(Optional.of(subject));
        when(modelMapper.map(requestCreateExamDTO, Exam.class)).thenReturn(exam);
        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        Exam createdExam = examService.createExam(requestCreateExamDTO);

        assertNotNull(createdExam);
        assertEquals(exam.getId(), createdExam.getId());
        verify(subjectRepository, times(1)).findById(requestCreateExamDTO.getSubjectId());
        verify(examRepository, times(1)).save(any(Exam.class));
    }

    @Test
    void testCreateExam_SubjectNotFound() {
        when(subjectRepository.findById(requestCreateExamDTO.getSubjectId())).thenReturn(Optional.empty());

        assertThrows(SubjectNotFoundException.class, () -> examService.createExam(requestCreateExamDTO));

        verify(subjectRepository, times(1)).findById(requestCreateExamDTO.getSubjectId());
        verify(examRepository, never()).save(any(Exam.class));
    }

    @Test
    void testGetAllExams() {
        List<Exam> examList = Collections.singletonList(exam);
        when(examRepository.findAll()).thenReturn(examList);

        List<Exam> retrievedExams = examService.getAllExams();

        assertNotNull(retrievedExams);
        assertEquals(1, retrievedExams.size());
        verify(examRepository, times(1)).findAll();
    }

    @Test
    void testGetExam() {
        when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));

        Exam retrievedExam = examService.getExam(exam.getId());

        assertNotNull(retrievedExam);
        assertEquals(exam.getId(), retrievedExam.getId());
        verify(examRepository, times(1)).findById(exam.getId());
    }

    @Test
    void testGetExam_NotFound() {
        when(examRepository.findById(exam.getId())).thenReturn(Optional.empty());

        assertThrows(ExamNotFoundException.class, () -> examService.getExam(exam.getId()));

        verify(examRepository, times(1)).findById(exam.getId());
    }

    @Test
    void testDeleteExam() {
        when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));

        examService.deleteExam(exam.getId());

        verify(examRepository, times(1)).findById(exam.getId());
        verify(examRepository, times(1)).deleteById(exam.getId());
    }

    @Test
    void testDeleteExam_NotFound() {
        when(examRepository.findById(exam.getId())).thenReturn(Optional.empty());

        assertThrows(ExamNotFoundException.class, () -> examService.deleteExam(exam.getId()));

        verify(examRepository, times(1)).findById(exam.getId());
        verify(examRepository, never()).deleteById(exam.getId());
    }

}
