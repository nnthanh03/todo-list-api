package com.example.todo_list_api.repository;

import com.example.todo_list_api.entity.Dependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependencyRepository extends JpaRepository<Dependency, Integer> {
//    void deleteById(Integer id);
    List<Dependency> findBytaskId(String task_id);

}
