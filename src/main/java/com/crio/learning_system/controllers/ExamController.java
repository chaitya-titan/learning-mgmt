package com.crio.learning_system.controllers;

import com.crio.learning_system.dtos.RequestCreateExamDTO;
import com.crio.learning_system.entities.Exam;
import com.crio.learning_system.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("")
    public ResponseEntity<Exam> createExam(@RequestBody RequestCreateExamDTO requestCreateExamDTO) {
        Exam exam = examService.createExam(requestCreateExamDTO);
        return ResponseEntity.created(null).body(exam);
    }

    @GetMapping("")
    public ResponseEntity<List<Exam>> getAllExams(){
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExam(@PathVariable String examId) {
        Exam exam = examService.getExam(examId);
        return ResponseEntity.ok(exam);
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<String> deleteExam(@PathVariable String examId) {
        examService.deleteExam(examId);
        return ResponseEntity.ok("Exams deleted successfully");
    }

}
