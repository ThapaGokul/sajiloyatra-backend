package com.example.SajiloyatraImplementation.controller;

import com.example.SajiloyatraImplementation.model.User;
import com.example.SajiloyatraImplementation.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Get currently logged-in user's profile
    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        // Hide password before returning
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    // Update currently logged-in user's profile
    @PutMapping("/update")
    public ResponseEntity<User> updateProfile(@RequestBody User updatedUser, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();

        // Update fields if provided
        if (updatedUser.getFirstName() != null) currentUser.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null) currentUser.setLastName(updatedUser.getLastName());
        if (updatedUser.getPhoneNumber() != null) currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            // Encode the new password
            currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        User savedUser = userRepository.save(currentUser);
        savedUser.setPassword(null); // Hide password
        return ResponseEntity.ok(savedUser);
    }

    // Optional: Get any user by ID (admin-only, example)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(null);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}