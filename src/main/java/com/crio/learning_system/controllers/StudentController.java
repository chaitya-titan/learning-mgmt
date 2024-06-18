package com.crio.learning_system.controllers;

import com.crio.learning_system.dtos.RegisterStudentDTO;
import com.crio.learning_system.entities.Student;
import com.crio.learning_system.exceptions.StudentNotFoundException;
import com.crio.learning_system.exceptions.SubjectAlreadyRegisteredException;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import com.crio.learning_system.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("")
    public ResponseEntity<Student> registerStudent(@RequestBody RegisterStudentDTO registerStudentDTO) {
        Student student = studentService.registerStudent(registerStudentDTO);
        return ResponseEntity.created(null).body(student);
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) throws StudentNotFoundException {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId) throws StudentNotFoundException {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @PostMapping("/{studentId}/register/{subjectId}")
    public ResponseEntity<Student>
        registerStudentToSubject(
            @PathVariable String studentId, @PathVariable String subjectId)
            throws StudentNotFoundException, SubjectNotFoundException, SubjectAlreadyRegisteredException {
        Student student = studentService.registerStudentToSubject(studentId, subjectId);
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/{studentId}/exam/{examId}")
    public ResponseEntity<Student>
        enrollStudentToExam(
            @PathVariable String studentId, @PathVariable String examId)
            throws StudentNotFoundException, SubjectNotFoundException, SubjectAlreadyRegisteredException {
        Student student = studentService.enrollStudentToExam(studentId, examId);
        return ResponseEntity.ok().body(student);
    }

}
