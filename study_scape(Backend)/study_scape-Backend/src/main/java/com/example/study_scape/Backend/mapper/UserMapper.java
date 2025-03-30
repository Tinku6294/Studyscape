package com.example.study_scape.Backend.mapper;

import com.example.study_scape.Backend.dto.UserDTO;
import com.example.study_scape.Backend.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserMapper {

    // Convert UserDTO to User entity
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setUuid(Optional.ofNullable(dto.getUuid()).orElse(UUID.randomUUID()));
        user.setUsername(Optional.ofNullable(dto.getUsername()).orElse("DefaultUser"));
        user.setEmail(Optional.ofNullable(dto.getEmail()).orElse("default@example.com"));
        user.setPhone(Optional.ofNullable(dto.getPhone()).orElse(null));
        user.setAddress(Optional.ofNullable(dto.getAddress()).orElse("Default Address"));
        user.setPassword(Optional.ofNullable(dto.getPassword()).orElse(null));
        user.setRole(Optional.ofNullable(dto.getRole()).orElse(null));

        return user;
    }

    // Convert User entity to UserDTO
    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getUuid(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getPassword(),
                user.getRole()
        );
    }

    // Update existing User from UserDTO
    public User updateFromDto(UserDTO dto, User existingUser) {
        Optional.ofNullable(dto.getUsername()).ifPresent(existingUser::setUsername);
        Optional.ofNullable(dto.getEmail()).ifPresent(existingUser::setEmail);
        Optional.ofNullable(dto.getPhone()).ifPresent(existingUser::setPhone);
        Optional.ofNullable(dto.getAddress()).ifPresent(existingUser::setAddress);
        Optional.ofNullable(dto.getPassword()).ifPresent(existingUser::setPassword);
        Optional.ofNullable(dto.getRole()).ifPresent(existingUser::setRole);

        return existingUser;
    }
}