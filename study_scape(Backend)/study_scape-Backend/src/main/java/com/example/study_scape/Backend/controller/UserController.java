package com.example.study_scape.Backend.controller;
import com.example.study_scape.Backend.dto.ApiResponse;
import com.example.study_scape.Backend.dto.UserDTO;
import com.example.study_scape.Backend.exception.UserAlreadyExistsException;
import com.example.study_scape.Backend.exception.UserNotFoundException;
import com.example.study_scape.Backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "User created successfully", createdUser));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable UUID uuid, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(uuid, userDTO);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully", updatedUser));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByUuid(@PathVariable UUID uuid) {
        UserDTO user = userService.getUserByUuid(uuid);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "User found", user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "User found", user));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "User found", user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Users retrieved successfully", users));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID uuid) {
        userService.deleteUser(uuid);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully", null));
    }

    @GetMapping("/check/email/{email}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkEmailAvailability(@PathVariable String email) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !userService.isEmailTaken(email));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Email availability checked", response));
    }

    @GetMapping("/check/username/{username}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkUsernameAvailability(@PathVariable String username) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !userService.isUsernameTaken(username));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Username availability checked", response));
    }

    @GetMapping("/check/phone/{phone}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkPhoneAvailability(@PathVariable Long phone) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !userService.isPhoneTaken(phone));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Phone number availability checked", response));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null));
    }
}
