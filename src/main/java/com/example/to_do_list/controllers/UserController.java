package com.example.to_do_list.controllers;

import com.example.to_do_list.domain.task.Task;
import com.example.to_do_list.domain.user.User;
import com.example.to_do_list.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<User>> getTasks() {
        List<User> tasks = repository.findAllByIsActiveTrue();
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            User userToUpdate = user.get();
            userToUpdate.deactivate();
            repository.save(userToUpdate);
            return ResponseEntity.ok(userToUpdate);
        }
    }
}
