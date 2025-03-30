package com.example.study_scape.Backend.service;

import com.example.study_scape.Backend.dto.UserDTO;
import com.example.study_scape.Backend.exception.UserAlreadyExistsException;
import com.example.study_scape.Backend.exception.UserNotFoundException;
import com.example.study_scape.Backend.mapper.UserMapper;
import com.example.study_scape.Backend.model.User;
import com.example.study_scape.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    // ✅ Create a new user with forced random UUID generation
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Existing validation checks
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered: " + userDTO.getEmail());
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username already taken: " + userDTO.getUsername());
        }
        if (userDTO.getPhone() != null && userRepository.existsByPhone(userDTO.getPhone())) {
            throw new UserAlreadyExistsException("Phone number already registered: " + userDTO.getPhone());
        }


        // Convert DTO to entity
        User user = userMapper.toEntity(userDTO);

        // Save user
        User savedUser = userRepository.save(user);

        // Convert saved entity back to DTO
        return userMapper.toDto(savedUser);
    }

    // ✅ Update user details with UUID protection
    @Override
    public UserDTO updateUser(UUID uuid, UserDTO updatedUserDTO) {
        // Ensure the UUID cannot be changed
        User existingUser = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found with UUID: " + uuid));

        // Remove any UUID from the DTO to prevent accidental modification
        updatedUserDTO.setUuid(existingUser.getUuid());

        // Update user fields using mapper
        User updatedUser = userMapper.updateFromDto(updatedUserDTO, existingUser);
        User savedUser = userRepository.save(updatedUser);

        return userMapper.toDto(savedUser);
    }

    // Remaining methods stay the same as in the previous implementation
    @Override
    public UserDTO getUserByUuid(UUID uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found with UUID: " + uuid));
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return userMapper.toDto(user);
    }
    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return userMapper.toDto(user);
    }
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UUID uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found with UUID: " + uuid));
        userRepository.delete(user);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isPhoneTaken(Long phone) {
        return userRepository.existsByPhone(phone);
    }
}