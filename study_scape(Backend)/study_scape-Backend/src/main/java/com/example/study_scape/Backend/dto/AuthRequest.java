package com.example.study_scape.Backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;
}
