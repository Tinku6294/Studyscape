package com.example.study_scape.Backend.controller;

import com.example.study_scape.Backend.dto.ApiResponse;
import com.example.study_scape.Backend.model.Role;
import com.example.study_scape.Backend.service.RoleService;
import com.example.study_scape.Backend.exception.RoleAlreadyExistsException;
import com.example.study_scape.Backend.exception.RoleNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Role>> createRole(@Valid @RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Role created successfully", createdRole));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ApiResponse<Role>> updateRole(@PathVariable UUID uuid, @Valid @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(uuid, role);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Role updated successfully", updatedRole));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<Role>> getRoleByUuid(@PathVariable UUID uuid) {
        Role role = roleService.getRoleByUuid(uuid);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Role found", role));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Role>> getRoleByName(@PathVariable String name) {
        Role role = roleService.getRoleByName(name);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Role found", role));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Role>>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Roles retrieved successfully", roles));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable UUID uuid) {
        roleService.deleteRole(uuid);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Role deleted successfully", null));
    }

    @GetMapping("/check/name/{name}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkRoleNameAvailability(@PathVariable String name) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !roleService.isRoleNameTaken(name));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Role name availability checked", response));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleRoleNotFoundException(RoleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
    }

    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleRoleAlreadyExistsException(RoleAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null));
    }
}