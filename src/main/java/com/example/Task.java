package com.example;

import jakarta.persistence.*;

@Entity  // Marks this class as a JPA entity
@Table(name = "tasks")  // Optional: Specifies the table name
public class Task {

    @Id  // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private Long id;

    @Column(nullable = false)  // Maps to "title" column, cannot be null
    private String title;

    private String priority;
    private boolean completed;

    // Default Constructor (Required by JPA)
    public Task() {}

    // Constructor
    public Task(String title, String priority, boolean completed) {
        this.title = title;
        this.priority = priority;
        this.completed = completed;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}

