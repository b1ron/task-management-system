package com.example.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.model.Task;
import com.example.repository.TaskRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public void root() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tasks")
    public Collection<Task> get() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task get(@PathVariable("id") String id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return task.get();
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable("id") String id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
    }

    @PostMapping("/tasks")
    public void create(@RequestBody Task task) {
        taskRepository.save(task);
    }

}
