package com.example.study_scape.Backend.repository;
import com.example.study_scape.Backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    // Check if a role with the given name already exists
    boolean existsByName(String name);

    // Find a role by its name
    Optional<Role> findByName(String name);

    // Find a role by its UUID
    Optional<Role> findByUuid(UUID uuid);
}
