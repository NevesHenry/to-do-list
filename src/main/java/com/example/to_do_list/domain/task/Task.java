package com.example.to_do_list.domain.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Entity(name = "task")
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String status;
    private String created_at;
    private String updated_at;
    private boolean is_active;

    public Task(PostTaskDTO postTaskDTO) {
        this.title = postTaskDTO.title();
        this.description = postTaskDTO.description();
        this.status = Status.BACKLOG.name();
        this.created_at = LocalTime.now().toString();
        this.updated_at = LocalTime.now().toString();
        this.is_active = true;
    }

}
