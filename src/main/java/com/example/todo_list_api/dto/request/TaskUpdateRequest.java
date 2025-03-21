package com.example.todo_list_api.dto.request;

import com.example.todo_list_api.entity.Task;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class TaskUpdateRequest {
    private String title;
    private String description;
    private LocalDate due_date;

    @Enumerated(EnumType.STRING)
    private Task.Priority priority;
    private Task.Status status;
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

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public Task.Priority getPriority() {
        return priority;
    }

    public void setPriority(Task.Priority priority) {
        this.priority = priority;
    }

    public Task.Status getStatus() {
        return status;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }



}
