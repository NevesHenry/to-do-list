package com.example.to_do_list.controllers;

import com.example.to_do_list.domain.task.Task;
import com.example.to_do_list.domain.task.PostTaskDTO;
import com.example.to_do_list.domain.task.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository repository;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        var tasks = repository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Validated PostTaskDTO data) {
        System.out.println(data);
        Task task = new Task(data);
        repository.save(task);
        return ResponseEntity.ok(task);

    }

    @Transactional
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody @Validated PostTaskDTO data) {
        Optional<Task> task = repository.findById(data.id());
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task taskToUpdate = task.get();
        taskToUpdate.setTitle(data.title());
        taskToUpdate.setDescription(data.description());
        repository.save(taskToUpdate);
        return ResponseEntity.ok(taskToUpdate);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable String id) {
        repository.deleteById(Integer.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
