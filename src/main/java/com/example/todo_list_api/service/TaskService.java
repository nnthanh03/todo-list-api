package com.example.todo_list_api.service;

import com.example.todo_list_api.dto.request.TaskCreationRequest;
import com.example.todo_list_api.dto.request.TaskUpdateRequest;
import com.example.todo_list_api.entity.Task;
import com.example.todo_list_api.repository.TaskRepository;
import com.example.todo_list_api.specification.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public Task createTask(TaskCreationRequest request){
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        return taskRepository.save(task);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Task getTask(String id){
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found") );
    }

    public Page<Task> filterTask(String title, Task.Status status, Task.Priority priority, LocalDate dueDate, Pageable pageable) {
        Specification<Task> spec = TaskSpecification.filterTask(title, status, priority, dueDate);
        return taskRepository.findAll(spec, pageable);
    }

    public Task updateTask(String taskId, TaskUpdateRequest request){
        Task task = getTask(taskId);


        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        return taskRepository.save(task);

    }

    public void deleteTask(String taskId){
        taskRepository.deleteById(taskId);
    }

    public List<Task> getOverdueTask(){
        LocalDate now = LocalDate.now();
        return taskRepository.findByDueDateBefore(now);
    }

    public List<Task> getUpcomingTaskOneDay(){
        LocalDate now = LocalDate.now();
        LocalDate afterOneDay = now.plusDays(1);

        return taskRepository.findByDueDate(afterOneDay);
    }

//    @Scheduled(cron = "0 0 8 * * ?")
//    public void checkOverdueTasks() {
//        List<Task> overdueTasks = getOverdueTask();
//        System.out.println("Overdue tasks: " + overdueTasks);
//    }
//
//    @Scheduled(cron = "0 0 9 * * ?")
//    public void checkUpcomingTasks() {
//        List<Task> upcomingTasks = getUpcomingTaskOneDay();
//        System.out.println("Upcoming tasks for tomorrow: " + upcomingTasks);
//    }

}
