package com.udla.spring.boot.backend.taskmanager.apirest.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
