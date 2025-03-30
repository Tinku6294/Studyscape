package com.example.study_scape.Backend.service;

import com.example.study_scape.Backend.exception.RoleAlreadyExistsException;
import com.example.study_scape.Backend.exception.RoleNotFoundException;
import com.example.study_scape.Backend.model.Role;
import com.example.study_scape.Backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new RoleAlreadyExistsException("Role already exists: " + role.getName());
        }

        // Ensure UUID is set
        if (role.getUuid() == null) {
            role.setUuid(UUID.randomUUID());
        }

        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(UUID uuid, Role updatedRole) {
        Role existingRole = roleRepository.findByUuid(uuid)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with UUID: " + uuid));

        // Update fields that are allowed to be modified
        if (updatedRole.getName() != null) {
            existingRole.setName(updatedRole.getName());
        }
        if (updatedRole.getDescription() != null) {
            existingRole.setDescription(updatedRole.getDescription());
        }
        // Add any other fields you want to be updatable

        return roleRepository.save(existingRole);
    }

    @Override
    public Role getRoleByUuid(UUID uuid) {
        return roleRepository.findByUuid(uuid)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with UUID: " + uuid));
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with name: " + name));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(UUID uuid) {
        Role role = roleRepository.findByUuid(uuid)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with UUID: " + uuid));
        roleRepository.delete(role);
    }

    @Override
    public boolean isRoleNameTaken(String name) {
        return roleRepository.existsByName(name);
    }
}