package com.example.todo_list_api.controller;

import com.example.todo_list_api.dto.request.DependencyCreationRequest;
import com.example.todo_list_api.entity.Dependency;
import com.example.todo_list_api.service.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/depend")

public class DependencyController {
    @Autowired
    private DependencyService dependencyService;

    @PostMapping
    ResponseEntity<String> createDependency(@RequestBody DependencyCreationRequest request){
        if (dependencyService.checkCircularCreate(request.getTaskId(), request.getDependencyId(), new HashSet<>())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't create, Detect Circular Dependency");
        }
        dependencyService.createDependency(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dependency created successfully.");
    }

    @PostMapping("/check/{taskId}")
    ResponseEntity<String> checkCircularOfTask(@PathVariable String taskId){
        if (dependencyService.checkCircularOfTask(taskId, new HashSet<>())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Detect Circular Dependency");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("OK.");
    }

    @DeleteMapping("/delete/{id}")
    String deleteDependency(@PathVariable Integer id){
        dependencyService.deleteDependency(id);
        return "Delete successfully";
    }

    @GetMapping("/{taskId}")
    public List<Dependency> getAllDependencies(@PathVariable String taskId) {
        return dependencyService.getAllDependencies(taskId);
    }

}
