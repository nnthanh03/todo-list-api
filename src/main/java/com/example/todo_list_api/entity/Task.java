package com.example.todo_list_api.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;

    @Column(name = "due_date")

    private LocalDate dueDate;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate due_date) {
        this.dueDate = due_date;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    public Task() {
        this.priority = Priority.MEDIUM;
        this.status = Status.PENDING;
    }

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }
    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        OVERDUE,
        BLOCKED,
        CANCELLED
    }
}
