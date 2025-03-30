package com.example.study_scape.Backend.repository;

import com.example.study_scape.Backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    // Find student by email (used for authentication)
    Optional<Student> findByEmail(String email);

    // Find student by studentId (used for login)
    Optional<Student> findByStudentId(String studentId);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Check if studentId already exists
    boolean existsByStudentId(String studentId);

    // Check if collegeRoll already exists
    boolean existsByCollegeRoll(String collegeRoll);

    // Check if phone already exists
    boolean existsByPhone(String phone);

    // Find student by UUID
    Optional<Student> findByUuid(UUID uuid);
}
