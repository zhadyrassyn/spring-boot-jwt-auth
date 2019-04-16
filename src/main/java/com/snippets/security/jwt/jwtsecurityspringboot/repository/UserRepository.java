package com.snippets.security.jwt.jwtsecurityspringboot.repository;

import com.snippets.security.jwt.jwtsecurityspringboot.model.Role;
import com.snippets.security.jwt.jwtsecurityspringboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepository {

   public Optional<User> findByUsername(String username) {
      User user = new User();
      user.setUsername(username);
      user.setPassword("$2a$10$YsgqaBJVNZ7PrB6pL4X97uYX7AZUyNR6dae.Q5FQd/ZCbAj7V2A.m");
      user.setFirstName("Daniyar");
      user.setLastName("Ok");

      Set<Role> roles = new HashSet<>();
      if (user.equals("daniyar")) {
         roles.add(new Role("ADMIN"));
      } else {
         roles.add(new Role("USER"));
      }

      user.setRoles(roles);
      return Optional.of(user);
   }
}
