package com.example.todo_list_api.controller;

import com.example.todo_list_api.dto.request.TaskCreationRequest;
import com.example.todo_list_api.dto.request.TaskUpdateRequest;
import com.example.todo_list_api.entity.Task;
import com.example.todo_list_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    Task createTask(@RequestBody TaskCreationRequest request){
        return taskService.createTask(request);
    }

    @GetMapping("/all")
    List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    @GetMapping("/get/{taskId}")
    Task getTask(@PathVariable String taskId){return taskService.getTask(taskId);}

    @GetMapping("/filter")
    public Page<Task> filterTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Task.Status status,
            @RequestParam(required = false) Task.Priority priority,
            @RequestParam(required = false) LocalDate dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.filterTask(title, status, priority, dueDate, pageable);
    }

    @PutMapping("/update/{taskId}")
    Task updateTask(@PathVariable String taskId, @RequestBody TaskUpdateRequest request){

        return taskService.updateTask(taskId, request);
    }

    @DeleteMapping("/delete/{taskId}")
    String deleteTask(@PathVariable String taskId){
        taskService.deleteTask(taskId);
        return "Delete successfully";
    }

    @GetMapping("/overdue")
    List<Task> getOverdueTask(){
        return taskService.getOverdueTask();
    }

    @GetMapping("/upcoming")
    List<Task> getUpcomingTaskOneDay(){
        return taskService.getUpcomingTaskOneDay();
    }


}
