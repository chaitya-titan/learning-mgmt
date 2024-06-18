package com.crio.learning_system.exceptions;

public class ExamNotFoundException extends RuntimeException{
    public ExamNotFoundException(String message) {
        super(message);
    }
}
