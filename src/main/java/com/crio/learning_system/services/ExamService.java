package com.crio.learning_system.services;

import com.crio.learning_system.dtos.RequestCreateExamDTO;
import com.crio.learning_system.entities.Exam;
import com.crio.learning_system.entities.Subject;
import com.crio.learning_system.exceptions.ExamNotFoundException;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import com.crio.learning_system.repositories.ExamRepository;
import com.crio.learning_system.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExamService {
    @Autowired
    private final ExamRepository examRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SubjectRepository subjectRepository;

    public ExamService( SubjectRepository subjectRepository, ExamRepository examRepository, ModelMapper modelMapper) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
    }

    public Exam createExam(RequestCreateExamDTO requestCreateExamDTO) {
        Exam exam = modelMapper.map(requestCreateExamDTO, Exam.class);
        Subject subject = subjectRepository.findById(requestCreateExamDTO.getSubjectId()).orElseThrow(() ->
                new SubjectNotFoundException("Subject Not Found!"));
        exam.setId(UUID.randomUUID().toString());
        exam.setCreatedAt(new Date());
        exam.setSubjectName(subject);
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

    public Exam getExam(String examId) {
        return examRepository.findById(examId).orElseThrow(() -> new ExamNotFoundException("Exam Not Found!"));
    }

    public void deleteExam(String examId){
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new ExamNotFoundException("Exam Not Found!"));
        examRepository.deleteById(examId);
    }

}
