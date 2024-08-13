package com.example.to_do_list.controllers;

import com.example.to_do_list.domain.user.AuthenticationDTO;
import com.example.to_do_list.domain.user.LoginResponseDTO;
import com.example.to_do_list.domain.user.RegisterDTO;
import com.example.to_do_list.domain.user.User;
import com.example.to_do_list.infra.security.TokenService;
import com.example.to_do_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                data.username(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        String token = this.tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Validated RegisterDTO data) {
        if(this.userRepository.findByUsername(data.username()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.username(), encryptedPassword, data.role());
            this.userRepository.save(newUser);

            return ResponseEntity.ok().build();
        }
    }
}
