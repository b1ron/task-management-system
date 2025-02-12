package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TaskController {

    private List<Task> mock = Arrays.asList(new Task("1", "P2", false));

    @GetMapping("/tasks")
    public List<Task> get() {
        return mock;
    }

}
