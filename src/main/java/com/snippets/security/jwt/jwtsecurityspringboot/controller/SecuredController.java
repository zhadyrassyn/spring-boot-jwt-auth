package com.snippets.security.jwt.jwtsecurityspringboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured")
public class SecuredController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String helloForAdmin() {
        return "SUCCESS: Access available for authenticated admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String helloForUser() {
        return "SUCCESS: Access available for authenticated user";
    }
}
