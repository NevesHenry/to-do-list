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

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        var tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<HttpStatus> createTask(@RequestBody @Validated PostTaskDTO data) {
        System.out.println(data);
        taskRepository.save(new Task(data));
        return ResponseEntity.ok().build();

    }

    @Transactional
    @PutMapping
    public String updateTask() {
        return "Task updated";
    }

    @Transactional
    @DeleteMapping
    public String deleteTask() {
        return "Task deleted";
    }
}
