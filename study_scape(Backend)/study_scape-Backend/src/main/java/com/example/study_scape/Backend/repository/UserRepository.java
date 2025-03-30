package com.example.study_scape.Backend.repository;

import com.example.study_scape.Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // Find user by email (used for authentication)
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByPhone(Long phone);
    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByUsername(String username);
}
