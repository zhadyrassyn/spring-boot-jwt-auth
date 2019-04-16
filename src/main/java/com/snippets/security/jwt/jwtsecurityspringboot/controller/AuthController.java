package com.snippets.security.jwt.jwtsecurityspringboot.controller;

import com.snippets.security.jwt.jwtsecurityspringboot.model.Role;
import com.snippets.security.jwt.jwtsecurityspringboot.model.User;
import com.snippets.security.jwt.jwtsecurityspringboot.payload.ApiResponse;
import com.snippets.security.jwt.jwtsecurityspringboot.payload.JwtAuthenticationResponse;
import com.snippets.security.jwt.jwtsecurityspringboot.payload.LoginRequest;
import com.snippets.security.jwt.jwtsecurityspringboot.payload.SignupUser;
import com.snippets.security.jwt.jwtsecurityspringboot.repository.UserRepository;
import com.snippets.security.jwt.jwtsecurityspringboot.security.JwtTokenProvider;
import com.snippets.security.jwt.jwtsecurityspringboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupUser signupUser) {
        if (userRepository.existsByUsername(signupUser.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(signupUser.getUsername());
        user.setFirstName(signupUser.getFirstName());
        user.setLastName(signupUser.getLastName());
        user.setPassword(encoder.encode(signupUser.getPassword()));
        user.setRoles(Collections.singleton(new Role("USER")));

        return new ResponseEntity(new ApiResponse(true, "User registered successfully"),
                HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
