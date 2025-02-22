package com.example.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.RestController;
import com.example.exception.TaskNotFoundException;
import com.example.model.Task;
import com.example.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Collection<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        return ResponseEntity.ok(taskService.getTaskById(id)); // Exception handled globally
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id) {
        if (!taskService.deleteTask(id)) {
            throw new TaskNotFoundException(id);
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tasks")
    public void create(@RequestBody Task task) {
        taskService.createTask(task);
    }

}
