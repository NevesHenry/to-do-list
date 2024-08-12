package com.example.to_do_list.repository;

import com.example.to_do_list.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByUsername(String username);
    List<User> findAllByIsActiveTrue();
}
