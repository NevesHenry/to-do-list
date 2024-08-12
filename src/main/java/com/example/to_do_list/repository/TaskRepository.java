package com.example.to_do_list.repository;

import com.example.to_do_list.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByIsActiveTrue();
}
