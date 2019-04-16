package com.snippets.security.jwt.jwtsecurityspringboot.repository;

import com.snippets.security.jwt.jwtsecurityspringboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

   Optional<User> findByUsername(String username);

   boolean existsByUsername(String username);
}
