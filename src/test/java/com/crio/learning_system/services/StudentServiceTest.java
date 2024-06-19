package com.crio.learning_system.services;

import com.crio.learning_system.dtos.RegisterStudentDTO;
import com.crio.learning_system.entities.Exam;
import com.crio.learning_system.entities.Student;
import com.crio.learning_system.entities.Subject;
import com.crio.learning_system.exceptions.ExamNotFoundException;
import com.crio.learning_system.exceptions.StudentNotFoundException;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import com.crio.learning_system.repositories.ExamRepository;
import com.crio.learning_system.repositories.StudentRepository;
import com.crio.learning_system.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentService studentService;

    private RegisterStudentDTO registerStudentDTO;
    private Student student;
    private Subject subject;
    private Exam exam;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        registerStudentDTO = new RegisterStudentDTO();
        registerStudentDTO.setStudentName("John Doe");

        student = new Student();
        student.setId("student-id");
        student.setStudentName("John Doe");
        student.setCreatedAt(new Date());

        subject = new Subject();
        subject.setId("subject-id");
        subject.setSubjectName("Maths");

        exam = new Exam();
        exam.setId("exam-id");
        exam.setSubjectName(subject);
    }

    @Test
    void testRegisterStudent() {
        when(modelMapper.map(registerStudentDTO, Student.class)).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student registeredStudent = studentService.registerStudent(registerStudentDTO);

        assertNotNull(registeredStudent);
        assertEquals(student.getId(), registeredStudent.getId());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testGetAllStudent() {
        List<Student> students = Collections.singletonList(student);
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> retrievedStudents = studentService.getAllStudent();

        assertNotNull(retrievedStudents);
        assertEquals(1, retrievedStudents.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        studentService.deleteStudent(student.getId());

        verify(studentRepository, times(1)).findById(student.getId());
        verify(studentRepository, times(1)).deleteById(student.getId());
    }

    @Test
    void testDeleteStudent_NotFound() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent(student.getId()));

        verify(studentRepository, times(1)).findById(student.getId());
        verify(studentRepository, never()).deleteById(student.getId());
    }

    @Test
    void testGetStudentById() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        Student retrievedStudent = studentService.getStudentById(student.getId());

        assertNotNull(retrievedStudent);
        assertEquals(student.getId(), retrievedStudent.getId());
        verify(studentRepository, times(1)).findById(student.getId());
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(student.getId()));

        verify(studentRepository, times(1)).findById(student.getId());
    }

    @Test
    void testRegisterStudentToSubject() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Student updatedStudent = studentService.registerStudentToSubject(student.getId(), subject.getId());

        assertNotNull(updatedStudent);
        assertTrue(updatedStudent.getSubjects().contains(subject));
        verify(studentRepository, times(1)).findById(student.getId());
        verify(subjectRepository, times(1)).findById(subject.getId());
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void testRegisterStudentToSubject_StudentNotFound() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.registerStudentToSubject(student.getId(), subject.getId()));

        verify(studentRepository, times(1)).findById(student.getId());
        verify(subjectRepository, never()).findById(subject.getId());
        verify(studentRepository, never()).save(any(Student.class));
        verify(subjectRepository, never()).save(any(Subject.class));
    }

    @Test
    void testRegisterStudentToSubject_SubjectNotFound() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());

        assertThrows(SubjectNotFoundException.class, () -> studentService.registerStudentToSubject(student.getId(), subject.getId()));

        verify(studentRepository, times(1)).findById(student.getId());
        verify(subjectRepository, times(1)).findById(subject.getId());
        verify(studentRepository, never()).save(any(Student.class));
        verify(subjectRepository, never()).save(any(Subject.class));
    }

    @Test
    void testEnrollStudentToExam() {
        when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student updatedStudent = studentService.enrollStudentToExam(student.getId(), exam.getId());

        assertNotNull(updatedStudent);
        assertTrue(updatedStudent.getExams().contains(exam));
        verify(examRepository, times(1)).findById(exam.getId());
        verify(studentRepository, times(1)).findById(student.getId());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void testEnrollStudentToExam_ExamNotFound() {
        when(examRepository.findById(exam.getId())).thenReturn(Optional.empty());

        assertThrows(ExamNotFoundException.class, () -> studentService.enrollStudentToExam(student.getId(), exam.getId()));

        verify(examRepository, times(1)).findById(exam.getId());
        verify(studentRepository, never()).findById(student.getId());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void testEnrollStudentToExam_StudentNotFound() {
        when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.enrollStudentToExam(student.getId(), exam.getId()));

        verify(examRepository, times(1)).findById(exam.getId());
        verify(studentRepository, times(1)).findById(student.getId());
        verify(studentRepository, never()).save(any(Student.class));
    }

}
