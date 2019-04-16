package com.snippets.security.jwt.jwtsecurityspringboot.controller;

import com.snippets.security.jwt.jwtsecurityspringboot.model.JwtUser;
import com.snippets.security.jwt.jwtsecurityspringboot.security.JwtGenerator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    @PostMapping()
    public String generate(@RequestBody final JwtUser jwtUser) {
        JwtGenerator jwtGenerator = new JwtGenerator();

        return jwtGenerator.generate(jwtUser);
    }
}
