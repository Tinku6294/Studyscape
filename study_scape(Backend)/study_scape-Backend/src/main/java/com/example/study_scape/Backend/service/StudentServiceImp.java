package com.example.study_scape.Backend.service;

import com.example.study_scape.Backend.dto.StudentDTO;
import com.example.study_scape.Backend.exception.UserAlreadyExistsException;
import com.example.study_scape.Backend.exception.UserNotFoundException;
import com.example.study_scape.Backend.mapper.StudentMapper;
import com.example.study_scape.Backend.model.Student;
import com.example.study_scape.Backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    // ✅ Create a new student with forced random UUID generation
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        // ✅ Existing validation checks
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered: " + studentDTO.getEmail());
        }
        if (studentRepository.existsByStudentId(studentDTO.getStudentId())) {
            throw new UserAlreadyExistsException("Student ID already taken: " + studentDTO.getStudentId());
        }
        if (studentRepository.existsByCollegeRoll(studentDTO.getCollegeRoll())) {
            throw new UserAlreadyExistsException("College Roll already registered: " + studentDTO.getCollegeRoll());
        }
        if (studentRepository.existsByPhone(studentDTO.getPhone())) {
            throw new UserAlreadyExistsException("Phone number already registered: " + studentDTO.getPhone());
        }

        // ✅ Convert DTO to entity
        Student student = studentMapper.toEntity(studentDTO);

        // ✅ Encode Date of Birth as BCrypt Password
        String encodedPassword = passwordEncoder.encode(studentDTO.getDateOfBirth());
        student.setPassword(encodedPassword); // Set encoded DOB as password

        // ✅ Save student to database
        Student savedStudent = studentRepository.save(student);

        // ✅ Convert saved entity back to DTO
        return studentMapper.toDto(savedStudent);
    }


    // ✅ Update student details with UUID protection
    @Override
    public StudentDTO updateStudent(UUID uuid, StudentDTO updatedStudentDTO) {
        // Ensure the UUID cannot be changed
        Student existingStudent = studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("Student not found with UUID: " + uuid));

        // Remove any UUID from the DTO to prevent accidental modification
        updatedStudentDTO.setUuid(existingStudent.getUuid());

        // Update student fields using mapper
        Student updatedStudent = studentMapper.updateFromDto(updatedStudentDTO, existingStudent);
        Student savedStudent = studentRepository.save(updatedStudent);

        return studentMapper.toDto(savedStudent);
    }

    // ✅ Get student by UUID
    @Override
    public StudentDTO getStudentByUuid(UUID uuid) {
        Student student = studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("Student not found with UUID: " + uuid));
        return studentMapper.toDto(student);
    }

    // ✅ Get student by email
    @Override
    public StudentDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Student not found with email: " + email));
        return studentMapper.toDto(student);
    }

    // ✅ Get student by studentId
    @Override
    public StudentDTO getStudentByStudentId(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UserNotFoundException("Student not found with ID: " + studentId));
        return studentMapper.toDto(student);
    }

    // ✅ Get all students
    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Delete student by UUID
    @Override
    public void deleteStudent(UUID uuid) {
        Student student = studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("Student not found with UUID: " + uuid));
        studentRepository.delete(student);
    }

    // ✅ Check if email is already registered
    @Override
    public boolean isEmailTaken(String email) {
        return studentRepository.existsByEmail(email);
    }

    // ✅ Check if studentId is already taken
    @Override
    public boolean isStudentIdTaken(String studentId) {
        return studentRepository.existsByStudentId(studentId);
    }

    // ✅ Check if collegeRoll is already taken
    @Override
    public boolean isCollegeRollTaken(String collegeRoll) {
        return studentRepository.existsByCollegeRoll(collegeRoll);
    }

    // ✅ Check if phone is already registered
    @Override
    public boolean isPhoneTaken(String phone) {
        return studentRepository.existsByPhone(phone);
    }
}
