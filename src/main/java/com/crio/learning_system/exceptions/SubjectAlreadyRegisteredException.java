package com.crio.learning_system.exceptions;

public class SubjectAlreadyRegisteredException extends RuntimeException{

    public SubjectAlreadyRegisteredException(String message) {
        super(message);
    }
}
