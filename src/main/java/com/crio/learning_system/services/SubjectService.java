package com.crio.learning_system.services;

import com.crio.learning_system.dtos.CreateSubjectDTO;
import com.crio.learning_system.entities.Subject;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import com.crio.learning_system.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {
    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;

    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    public Subject createSubject(CreateSubjectDTO createSubjectDTO){
        Subject subject = modelMapper.map(createSubjectDTO, Subject.class);
        subject.setId(UUID.randomUUID().toString());
        subject.setCreatedAt(new Date());
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }

    public void deleteSubject(String subjectId){
        subjectRepository.findById(subjectId).orElseThrow(() ->
                new SubjectNotFoundException("Subject Not Found!"));
        subjectRepository.deleteById(subjectId);
    }

    public Subject getSubjectById(String subjectId){
        return subjectRepository.findById(subjectId).
                orElseThrow(() -> new SubjectNotFoundException("Subject Not Found!"));
    }
}
