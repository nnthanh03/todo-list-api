package com.example.todo_list_api.dto.request;

public class DependencyCreationRequest {
    private String taskId;
    private String dependencyId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(String dependencyId) {
        this.dependencyId = dependencyId;
    }
}
