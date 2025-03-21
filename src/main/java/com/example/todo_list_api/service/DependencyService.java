package com.example.todo_list_api.service;

import com.example.todo_list_api.dto.request.DependencyCreationRequest;
import com.example.todo_list_api.entity.Dependency;
import com.example.todo_list_api.repository.DependencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DependencyService {
    @Autowired
    private DependencyRepository dependencyRepository;

    public Dependency createDependency(DependencyCreationRequest request){
        Dependency dependency = new Dependency();

        dependency.setTaskId(request.getTaskId());
        dependency.setDependencyId(request.getDependencyId());

        return dependencyRepository.save(dependency);
    }

    public List<Dependency> getAllDependencies(String taskId) {
        return findDependencies(taskId, new ArrayList<>(), new HashSet<>());
    }

    private List<Dependency> findDependencies(String taskId, List<Dependency> allDependencies, Set<String> setCheck) {
        if (setCheck.contains(taskId)) {
            return allDependencies;
        }
        setCheck.add(taskId);

        List<Dependency> directDependencies = dependencyRepository.findBytaskId(taskId);
        allDependencies.addAll(directDependencies);

        for (Dependency dep : directDependencies) {
            findDependencies(dep.getDependencyId(), allDependencies, setCheck);
        }

        return allDependencies;
    }

    public boolean checkCircularOfTask(String taskId, Set<String> setCheck) {
        if (setCheck.contains(taskId)) {
            return true;
        }
        setCheck.add(taskId);

        List<Dependency> directDependencies = dependencyRepository.findBytaskId(taskId);

        for (Dependency dep : directDependencies) {
            if (checkCircularOfTask(dep.getDependencyId(), setCheck)) {
                return true;
            }
        }

        setCheck.remove(taskId);
        return false;
    }

    public boolean checkCircularCreate(String taskId, String dependenceId, Set<String> setCheck) {
        if (dependenceId.equals(taskId)) {
            return true;
        }

        if (setCheck.contains(dependenceId)) {
            return false;
        }

        setCheck.add(dependenceId);

        List<Dependency> directDependencies = dependencyRepository.findBytaskId(dependenceId);

        for (Dependency dep : directDependencies) {
            if (checkCircularCreate(taskId, dep.getDependencyId(), setCheck)) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public void deleteDependency(Integer id){
        dependencyRepository.deleteById(id);
    }

}
