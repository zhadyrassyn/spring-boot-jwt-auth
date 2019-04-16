package com.snippets.security.jwt.jwtsecurityspringboot.controller;

import com.snippets.security.jwt.jwtsecurityspringboot.payload.JwtAuthenticationResponse;
import com.snippets.security.jwt.jwtsecurityspringboot.payload.LoginRequest;
import com.snippets.security.jwt.jwtsecurityspringboot.security.JwtTokenProvider;
import com.snippets.security.jwt.jwtsecurityspringboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/{username}/{password}")
    public String signup(@PathVariable("username") String username,
                         @PathVariable("password") String password) {
        return authService.signup(username, password);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
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
