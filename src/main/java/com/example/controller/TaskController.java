package com.example.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.model.Task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class TaskController {

    private Map<String, Task> mock = new HashMap<String, Task>() {{
        put("1", new Task("1", "A", "HIGH", false));
        put("2", new Task("2", "B", "LOW", false));
    }};

    @GetMapping("/")
    public void root() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tasks")
    public Collection<Task> get() {
        return mock.values();
    }

    @GetMapping("/tasks/{id}")
    public Task get(@PathVariable("id") String id) {
        Task task = mock.get(id);
        if (task == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return task;
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable("id") String id) {
        Task task = mock.remove(id);
        if (task == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/tasks/")
    public Task create(Task task) {
        task.setId(UUID.randomUUID().toString());
        mock.put(task.getId(), task);
        return task;
    }

}
