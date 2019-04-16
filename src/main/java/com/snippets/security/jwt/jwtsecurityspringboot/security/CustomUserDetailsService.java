package com.snippets.security.jwt.jwtsecurityspringboot.security;

import com.snippets.security.jwt.jwtsecurityspringboot.model.User;
import com.snippets.security.jwt.jwtsecurityspringboot.model.UserPrincipal;
import com.snippets.security.jwt.jwtsecurityspringboot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("HERE...");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        System.out.println("HERE: " + user);
        return UserPrincipal.create(user);
    }
}
