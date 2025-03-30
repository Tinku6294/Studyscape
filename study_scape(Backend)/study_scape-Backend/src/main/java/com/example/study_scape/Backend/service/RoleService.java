package com.example.study_scape.Backend.service;

import com.example.study_scape.Backend.model.Role;
import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(UUID uuid, Role updatedRole);
    Role getRoleByUuid(UUID uuid);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
    void deleteRole(UUID uuid);
    boolean isRoleNameTaken(String name);
}