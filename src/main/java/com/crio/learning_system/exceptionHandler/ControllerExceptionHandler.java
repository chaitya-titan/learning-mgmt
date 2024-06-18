package com.crio.learning_system.exceptionHandler;

import com.crio.learning_system.exceptions.ExamNotFoundException;
import com.crio.learning_system.exceptions.StudentNotFoundException;
import com.crio.learning_system.exceptions.SubjectAlreadyRegisteredException;
import com.crio.learning_system.exceptions.SubjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    ResponseEntity<String> handleException(StudentNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    ResponseEntity<String> handleException(SubjectNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(SubjectAlreadyRegisteredException.class)
    ResponseEntity<String> handleException(SubjectAlreadyRegisteredException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(ExamNotFoundException.class)
    ResponseEntity<String> handleException(ExamNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
