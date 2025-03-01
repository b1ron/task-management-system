package com.example.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity // Marks this class as a JPA entity
@Table(name = "tasks") // Optional: Specifies the table name
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id; // Auto-generated String ID

    @Column(nullable = false) // Maps to "title" column, cannot be null
    private String title;

    private String priority;
    private boolean completed;
    private boolean requiresApproval;

    // Default Constructor (Required by JPA)
    public Task() {
    }

    // Constructor
    public Task(String id, String title, String priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.completed = completed;
        this.requiresApproval = false;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isRquiresApproval() {
        return requiresApproval;
    }

    public void setPriority(boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
//
