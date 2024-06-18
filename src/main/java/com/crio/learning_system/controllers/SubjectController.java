package com.crio.learning_system.controllers;

import com.crio.learning_system.dtos.CreateSubjectDTO;
import com.crio.learning_system.entities.Subject;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import com.crio.learning_system.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("")
    public ResponseEntity<List<Subject>> getAllSubject(){
        List<Subject> subjects = subjectService.getAllSubject();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable String subjectId)
            throws SubjectNotFoundException {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("")
    public ResponseEntity<Subject> createSubject(@RequestBody CreateSubjectDTO createSubjectDTO) {
        Subject subject = subjectService.createSubject(createSubjectDTO);
        return ResponseEntity.created(null).body(subject);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String subjectId)
            throws SubjectNotFoundException {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }
}
