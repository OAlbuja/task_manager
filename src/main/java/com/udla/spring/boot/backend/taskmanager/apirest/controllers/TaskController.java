package com.udla.spring.boot.backend.taskmanager.apirest.controllers;

import com.udla.spring.boot.backend.taskmanager.apirest.exceptions.TaskNotFoundException;
import com.udla.spring.boot.backend.taskmanager.apirest.models.entity.Task;
import com.udla.spring.boot.backend.taskmanager.apirest.models.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    // Obtener todas las tareas
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    // Obtener una tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("No se encontró la tarea con el ID proporcionado."));
        return ResponseEntity.ok(task);
    }

    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.save(task);
        return ResponseEntity.status(201).body(savedTask);
    }

    // Actualizar una tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("No se encontró la tarea con el ID proporcionado para actualizar."));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        Task updatedTask = taskService.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    // Eliminar una tarea por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("No se encontró la tarea con el ID proporcionado para eliminar."));
        taskService.deleteById(task.getId());
        return ResponseEntity.noContent().build();
    }

    // Obtener tareas por estado
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskService.findByStatus(status);
    }
}
