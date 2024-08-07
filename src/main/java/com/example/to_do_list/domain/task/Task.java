package com.example.to_do_list.domain.task;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity(name = "task")
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;
    private boolean isActive;

    public Task(PostTaskDTO postTaskDTO) {
        this.title = postTaskDTO.title();
        this.description = postTaskDTO.description();
        this.status = Status.BACKLOG.name();
        this.createdAt = LocalTime.now().toString();
        this.updatedAt = LocalTime.now().toString();
        this.isActive = true;
    }
}
