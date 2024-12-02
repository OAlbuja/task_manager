package com.udla.spring.boot.backend.taskmanager.apirest.models.service;

import com.udla.spring.boot.backend.taskmanager.apirest.models.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
    List<Task> findByStatus(String status);
}
