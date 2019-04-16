package com.snippets.security.jwt.jwtsecurityspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/open/cities")
public class OpenController {

    @GetMapping()
    public List<String> cities() {
        return Arrays.asList("Karaganda", "Qyzylorda");
    }
}
