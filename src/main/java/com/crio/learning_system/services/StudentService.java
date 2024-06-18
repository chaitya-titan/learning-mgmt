package com.crio.learning_system.services;

import com.crio.learning_system.dtos.RegisterStudentDTO;
import com.crio.learning_system.entities.Exam;
import com.crio.learning_system.entities.Student;
import com.crio.learning_system.entities.Subject;
import com.crio.learning_system.exceptions.ExamNotFoundException;
import com.crio.learning_system.exceptions.StudentNotFoundException;
import com.crio.learning_system.exceptions.SubjectAlreadyRegisteredException;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import com.crio.learning_system.repositories.ExamRepository;
import com.crio.learning_system.repositories.StudentRepository;
import com.crio.learning_system.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private final ExamRepository examRepository;
    @Autowired
    private ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, ExamRepository examRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
    }

    public Student registerStudent(RegisterStudentDTO registerStudentDTO){
        Student student = modelMapper.map(registerStudentDTO, Student.class);
        student.setId(UUID.randomUUID().toString());
        student.setCreatedAt(new Date());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public void deleteStudent(String studentId){
        studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student Not Found!"));
        studentRepository.deleteById(studentId);
    }

    public Student getStudentById(String studentId){
        return studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student Not Found!"));
    }

    public Student registerStudentToSubject(String studentId, String subjectId){
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student Not Found!"));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() ->
                new SubjectNotFoundException("Subject Not Found!"));


        student.getSubjects().add(subject);
        subject.getRegisteredStudents().add(student);

        subjectRepository.save(subject);
        return studentRepository.save(student);
    }

    public Student enrollStudentToExam(String studentId, String examId){
        Exam exam = examRepository.findById(examId).orElseThrow(() ->
                new ExamNotFoundException("Exam Not Found!"));
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException("Student Not Found!"));
        student.getExams().add(exam);
        exam.getStudents().add(student);
        return studentRepository.save(student);
    }
}
