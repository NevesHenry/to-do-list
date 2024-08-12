package com.example.to_do_list.domain.task;

import lombok.Getter;

@Getter
public enum Status {
    BACKLOG("backlog"),
    TODO("todo"),
    IN_PROGRESS("in_progress"),
    DONE("done");

    private final String status;

    Status(String status) {
        this.status = status;
    }

}
