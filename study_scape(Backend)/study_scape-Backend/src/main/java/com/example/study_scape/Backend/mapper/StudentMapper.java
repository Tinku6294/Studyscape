package com.example.study_scape.Backend.mapper;

import com.example.study_scape.Backend.dto.StudentDTO;
import com.example.study_scape.Backend.model.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class StudentMapper {

    // Convert StudentDTO to Student entity
    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setUuid(Optional.ofNullable(dto.getUuid()).orElse(UUID.randomUUID()));
        student.setStudentName(Optional.ofNullable(dto.getStudentName()).orElse("Default Student"));
        student.setStudentId(Optional.ofNullable(dto.getStudentId()).orElse("S12345"));
        student.setEmail(Optional.ofNullable(dto.getEmail()).orElse("default@example.com"));
        student.setCollegeRoll(Optional.ofNullable(dto.getCollegeRoll()).orElse("CR0001"));
        student.setCourseName(Optional.ofNullable(dto.getCourseName()).orElse("Default Course"));
        student.setPhone(Optional.ofNullable(dto.getPhone()).orElse("0000000000"));
        student.setDateOfBirth(Optional.ofNullable(dto.getDateOfBirth()).orElse("2000-01-01"));
        student.setSemester(Optional.ofNullable(dto.getSemester()).orElse("1"));
        student.setPassword(Optional.ofNullable(dto.getPassword()).orElse(null));
     //   student.setRole(Optional.ofNullable(dto.getRole()).orElse(null));

        return student;
    }

    // Convert Student entity to StudentDTO
    public StudentDTO toDto(Student student) {
        return new StudentDTO(
                student.getUuid(),
                student.getStudentName(),
                student.getStudentId(),
                student.getEmail(),
                student.getCollegeRoll(),
                student.getCourseName(),
                student.getPhone(),
                student.getDateOfBirth(),
                student.getSemester(),
                student.getPassword()
              //  student.getRole()
        );
    }

    // Update existing Student from StudentDTO
    public Student updateFromDto(StudentDTO dto, Student existingStudent) {
        Optional.ofNullable(dto.getStudentName()).ifPresent(existingStudent::setStudentName);
        Optional.ofNullable(dto.getStudentId()).ifPresent(existingStudent::setStudentId);
        Optional.ofNullable(dto.getEmail()).ifPresent(existingStudent::setEmail);
        Optional.ofNullable(dto.getCollegeRoll()).ifPresent(existingStudent::setCollegeRoll);
        Optional.ofNullable(dto.getCourseName()).ifPresent(existingStudent::setCourseName);
        Optional.ofNullable(dto.getPhone()).ifPresent(existingStudent::setPhone);
        Optional.ofNullable(dto.getDateOfBirth()).ifPresent(existingStudent::setDateOfBirth);
        Optional.ofNullable(dto.getSemester()).ifPresent(existingStudent::setSemester);
        Optional.ofNullable(dto.getPassword()).ifPresent(existingStudent::setPassword);
      //  Optional.ofNullable(dto.getRole()).ifPresent(existingStudent::setRole);

        return existingStudent;
    }
}
