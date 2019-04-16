package com.snippets.security.jwt.jwtsecurityspringboot.security;

import com.snippets.security.jwt.jwtsecurityspringboot.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    public JwtUser validate(String token) {

        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey("youtube")
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId((Long.parseLong((String)body.get("userId"))));
            jwtUser.setRole((String)body.get("role"));
        } catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
