package com.example.study_scape.Backend.dto;

import com.example.study_scape.Backend.model.Role;
import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private UUID uuid;
    private String studentName;
    private String studentId;
    private String email;
    private String collegeRoll;
    private String courseName;
    private String phone;
    private String dateOfBirth;
    private String semester;
    private String password;
//    private Role role;
}
