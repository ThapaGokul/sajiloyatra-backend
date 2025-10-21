package com.example.SajiloyatraImplementation.repository;

import com.example.SajiloyatraImplementation.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long userId); // works because Profile.user maps to User.id
}