package com.example.study_scape.Backend.security;

import com.example.study_scape.Backend.model.Student;
import com.example.study_scape.Backend.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomStudentDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public CustomStudentDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        // ✅ Fetch student by studentId
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found with studentId: " + studentId));

        // ✅ Return CustomStudentDetails
        return new CustomStudentDetails(student);
    }
}
