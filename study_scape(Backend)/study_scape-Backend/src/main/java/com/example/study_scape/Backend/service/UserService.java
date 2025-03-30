package com.example.study_scape.Backend.service;

import com.example.study_scape.Backend.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    // ✅ Create a new user
    UserDTO createUser(UserDTO userDTO);

    // ✅ Update user details
    UserDTO updateUser(UUID uuid, UserDTO updatedUserDTO);

    // ✅ Get user by UUID
    UserDTO getUserByUuid(UUID uuid);

    // ✅ Get user by email
    UserDTO getUserByEmail(String email);

    UserDTO getUserByUsername(String username);

    // ✅ Get all users
    List<UserDTO> getAllUsers();

    // ✅ Delete user by UUID
    void deleteUser(UUID uuid);

    // ✅ Check if email is taken
    boolean isEmailTaken(String email);

    // ✅ Check if username is taken
    boolean isUsernameTaken(String username);

    // ✅ Check if phone number is taken
    boolean isPhoneTaken(Long phone);
}
