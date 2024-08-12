package com.example.to_do_list.domain.user;

public record RegisterDTO(String username, String password, UserRole role) {
}
