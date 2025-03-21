package com.example.todo_list_api.repository;

import com.example.todo_list_api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {
    List<Task> findByDueDateBefore(LocalDate now);
    List<Task> findByDueDate(LocalDate now);
}
