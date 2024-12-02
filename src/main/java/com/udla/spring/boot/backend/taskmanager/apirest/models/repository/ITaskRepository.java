package com.udla.spring.boot.backend.taskmanager.apirest.models.repository;

import com.udla.spring.boot.backend.taskmanager.apirest.models.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
}
