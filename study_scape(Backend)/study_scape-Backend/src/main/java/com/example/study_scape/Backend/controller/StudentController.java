package com.example.study_scape.Backend.controller;

import com.example.study_scape.Backend.dto.ApiResponse;
import com.example.study_scape.Backend.dto.StudentDTO;
import com.example.study_scape.Backend.exception.UserAlreadyExistsException;
import com.example.study_scape.Backend.exception.UserNotFoundException;
import com.example.study_scape.Backend.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // ✅ Enable CORS for Frontend
public class StudentController {

    @Autowired
    private final StudentService studentService;

    // ✅ Create a new student
    @PostMapping
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO createdStudent = studentService.createStudent(studentDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(HttpStatus.CREATED.value(), "🎉 Student created successfully!", createdStudent));
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null));
        }
    }

    // ✅ Update student details
    @PutMapping("/{uuid}")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudent(@PathVariable String uuid, @Valid @RequestBody StudentDTO studentDTO) {
        try {
            UUID studentUuid = UUID.fromString(uuid);
            StudentDTO updatedStudent = studentService.updateStudent(studentUuid, studentDTO);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Student updated successfully", updatedStudent));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "❌ Invalid UUID format", null));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    // ✅ Get student by UUID
    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentByUuid(@PathVariable String uuid) {
        try {
            UUID studentUuid = UUID.fromString(uuid);
            StudentDTO student = studentService.getStudentByUuid(studentUuid);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Student found", student));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "❌ Invalid UUID format", null));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    // ✅ Get student by email
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentByEmail(@PathVariable String email) {
        StudentDTO student = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Student found by email", student));
    }

    // ✅ Get student by studentId
    @GetMapping("/studentId/{studentId}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentByStudentId(@PathVariable String studentId) {
        StudentDTO student = studentService.getStudentByStudentId(studentId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Student found by student ID", student));
    }

    // ✅ Get all students
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ All students retrieved successfully", students));
    }

    // ✅ Delete student by UUID
    @DeleteMapping("/{uuid}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String uuid) {
        try {
            UUID studentUuid = UUID.fromString(uuid);
            studentService.deleteStudent(studentUuid);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Student deleted successfully", null));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "❌ Invalid UUID format", null));
        }
    }

    // ✅ Check email availability
    @GetMapping("/check/email/{email}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkEmailAvailability(@PathVariable String email) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !studentService.isEmailTaken(email));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Email availability checked", response));
    }

    // ✅ Check studentId availability
    @GetMapping("/check/studentId/{studentId}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkStudentIdAvailability(@PathVariable String studentId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !studentService.isStudentIdTaken(studentId));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Student ID availability checked", response));
    }

    // ✅ Check collegeRoll availability
    @GetMapping("/check/collegeRoll/{collegeRoll}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkCollegeRollAvailability(@PathVariable String collegeRoll) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !studentService.isCollegeRollTaken(collegeRoll));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ College Roll availability checked", response));
    }

    // ✅ Check phone availability
    @GetMapping("/check/phone/{phone}")
    public ResponseEntity<ApiResponse<Map<String, Boolean>>> checkPhoneAvailability(@PathVariable String phone) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !studentService.isPhoneTaken(phone));
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "✅ Phone number availability checked", response));
    }

    // ✅ Handle UserNotFoundException globally
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
    }

    // ✅ Handle UserAlreadyExistsException globally
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null));
    }

    // ✅ Handle IllegalArgumentException for UUID parsing
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "❌ Invalid UUID format", null));
    }
}
