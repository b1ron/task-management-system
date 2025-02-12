package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TaskController {

    private Map<String, Task> mock = new HashMap<String, Task>() {{
        put("1", new Task("1", "P1", false));
        put("2", new Task("2", "P2", false));
    }};

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

}
