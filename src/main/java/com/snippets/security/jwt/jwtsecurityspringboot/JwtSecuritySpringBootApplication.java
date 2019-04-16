package com.snippets.security.jwt.jwtsecurityspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtSecuritySpringBootApplication {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return new BCryptPasswordEncoder().encode(rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return false;
			}
		};

		System.out.println(passwordEncoder.encode("123"));
		SpringApplication.run(JwtSecuritySpringBootApplication.class, args);
	}

}
