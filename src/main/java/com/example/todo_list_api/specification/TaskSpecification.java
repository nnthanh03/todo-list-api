package com.example.todo_list_api.specification;

import com.example.todo_list_api.entity.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TaskSpecification {
    public static Specification<Task> filterTask(String title, Task.Status status, Task.Priority priority, LocalDate due_date){
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();


            if (title != null && !title.isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("title"), "%" + title + "%"));
            }
            if (status != null) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }
            if (priority != null) {
                predicate = cb.and(predicate, cb.equal(root.get("priority"), priority));
            }
            if (due_date != null) {
                predicate = cb.and(predicate, cb.equal(root.get("due_date"), due_date));
            }

            return predicate;
        };
    }
}
