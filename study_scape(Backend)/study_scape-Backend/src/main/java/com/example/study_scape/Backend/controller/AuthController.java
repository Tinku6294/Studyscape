package com.example.study_scape.Backend.controller;

import com.example.study_scape.Backend.dto.ApiResponse;
import com.example.study_scape.Backend.dto.AuthRequest;
import com.example.study_scape.Backend.model.Student;
import com.example.study_scape.Backend.repository.StudentRepository;

import com.example.study_scape.Backend.security.JwtUtil;


import com.example.study_scape.Backend.service.StudentServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil; // ✅ Updated to use JwtUtil

    @Autowired
    private StudentServiceImp studentService;

//     ✅ Student Signup
@PostMapping("/signup")
public ResponseEntity<?> registerStudent(@Valid @RequestBody Student signUpRequest) {
    // Check if studentId already exists
    if (studentRepository.existsByStudentId(signUpRequest.getStudentId())) {
        return new ResponseEntity<>(new ApiResponse<>(false, "Student ID is already taken!"), HttpStatus.BAD_REQUEST);
    }

    // Check if email already exists
    if (studentRepository.existsByEmail(signUpRequest.getEmail())) {
        return new ResponseEntity<>(new ApiResponse<>(false, "Email is already in use!"), HttpStatus.BAD_REQUEST);
    }

    // Check if dateOfBirth is null or empty
    if (signUpRequest.getDateOfBirth() == null || signUpRequest.getDateOfBirth().isEmpty()) {
        return new ResponseEntity<>(new ApiResponse<>(false, "Date of Birth cannot be empty"), HttpStatus.BAD_REQUEST);
    }

    // ✅ Create new student account
    Student student = new Student();
    student.setStudentId(signUpRequest.getStudentId());
    student.setStudentName(signUpRequest.getStudentName());
    student.setEmail(signUpRequest.getEmail());
    student.setCollegeRoll(signUpRequest.getCollegeRoll());
    student.setCourseName(signUpRequest.getCourseName());
    student.setPhone(signUpRequest.getPhone());
    student.setPassword(passwordEncoder.encode(signUpRequest.getDateOfBirth()));
    student.setSemester(signUpRequest.getSemester());
    student.setPassword(signUpRequest.getPassword());
    // ✅ Encode dateOfBirth as password


    // ✅ Save student to database
    studentRepository.save(student);

    return new ResponseEntity<>(new ApiResponse<>(true, "Student registered successfully!"), HttpStatus.CREATED);
}


    // ✅ Student Login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateStudent(@Valid @RequestBody AuthRequest loginRequest) {
        try {
            // ✅ Lookup student explicitly for validation
            Student student = studentRepository.findByStudentId(loginRequest.getStudentId())
                    .orElseThrow(() -> new BadCredentialsException("Invalid student ID or password"));

            // ✅ Check if provided dateOfBirth matches stored password or hashed password
            if (!passwordEncoder.matches(loginRequest.getDateOfBirth(), student.getPassword())) {
                throw new BadCredentialsException("Invalid student ID or password");
            }

            // ✅ Authenticate student using UsernamePasswordAuthenticationToken
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getStudentId(),
                            loginRequest.getDateOfBirth()
                    )
            );

            // ✅ Set authentication in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // ✅ Generate JWT Token using JwtUtil
            String jwt = jwtUtil.generateToken(authentication);

            // ✅ Return successful login response with JWT token
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Login successful", jwt));

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "Authentication failed: " + e.getMessage(), null),
                    HttpStatus.UNAUTHORIZED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error during authentication: " + e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    // ✅ Logout
    @PostMapping("/logout")
    public ResponseEntity<?> logoutStudent() {
        // In JWT-based authentication, logout is typically handled client-side by removing the token
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Logout successful", null));
    }
}
