package com.example.study_scape.Backend.dto;

import com.example.study_scape.Backend.model.Role;
import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID uuid;
    private String username;
    private String email;
    private Long phone;
    private String address;
    private String password;
    private Role role;
}
