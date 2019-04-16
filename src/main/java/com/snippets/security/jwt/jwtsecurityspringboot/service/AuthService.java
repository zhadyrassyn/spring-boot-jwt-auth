package com.snippets.security.jwt.jwtsecurityspringboot.service;

public interface AuthService {
    String signup(String username, String password);

    String signin(String username, String password);
}
