package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Optional;

import com.example.exception.TaskNotFoundException;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

@ExtendWith(MockitoExtension.class) // Enables Mockito for JUnit 5
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // Mocking the repository

    @InjectMocks
    private TaskService taskService; // Injecting the mock into TaskService

    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task1 = new Task("1", "Task A", "HIGH", false);
        task2 = new Task("2", "Task B", "LOW", false);
    }

    @Test
    void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskById_Found() {
        when(taskRepository.findById("1")).thenReturn(Optional.of(task1));

        Task foundTask = taskService.getTaskById("1");

        assertEquals("Task A", foundTask.getTitle());
        verify(taskRepository, times(1)).findById("1");
    }

    @Test
    void testGetTaskById_NotFound() {
        when(taskRepository.findById("3")).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById("3"));
    }

    @Test
    void testCreateTask() {
        when(taskRepository.save(task1)).thenReturn(task1);

        Task savedTask = taskService.createTask(task1);

        assertNotNull(savedTask);
        assertEquals("Task A", savedTask.getTitle());
        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    void testDeleteTask_Found() {
        when(taskRepository.existsById("1")).thenReturn(true);

        taskService.deleteTask("1");

        verify(taskRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteTask_NotFound() {
        when(taskRepository.existsById("3")).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask("3"));
    }
}
