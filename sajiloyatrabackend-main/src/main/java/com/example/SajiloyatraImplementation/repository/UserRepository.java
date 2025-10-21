package com.example.SajiloyatraImplementation.repository;

import com.example.SajiloyatraImplementation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query to find a user by their email address
    Optional<User> findByEmail(String email);
}
