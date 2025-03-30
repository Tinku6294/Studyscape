package com.example.study_scape.Backend.service;

import com.example.study_scape.Backend.dto.StudentDTO;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    // ✅ Create a new student with forced random UUID generation
    StudentDTO createStudent(StudentDTO studentDTO);

    // ✅ Update student details with UUID protection
    StudentDTO updateStudent(UUID uuid, StudentDTO updatedStudentDTO);

    // ✅ Get student by UUID
    StudentDTO getStudentByUuid(UUID uuid);

    // ✅ Get student by email
    StudentDTO getStudentByEmail(String email);

    // ✅ Get student by studentId
    StudentDTO getStudentByStudentId(String studentId);

    // ✅ Get all students
    List<StudentDTO> getAllStudents();

    // ✅ Delete student by UUID
    void deleteStudent(UUID uuid);

    // ✅ Check if email is already registered
    boolean isEmailTaken(String email);

    // ✅ Check if studentId is already taken
    boolean isStudentIdTaken(String studentId);

    // ✅ Check if collegeRoll is already taken
    boolean isCollegeRollTaken(String collegeRoll);

    // ✅ Check if phone is already registered
    boolean isPhoneTaken(String phone);
}
