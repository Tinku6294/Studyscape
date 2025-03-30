package com.example.study_scape.Backend.security;

import com.example.study_scape.Backend.model.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomStudentDetails implements UserDetails {

    private String studentId;
    private String password;

    // ✅ Constructor to initialize from Student entity
    public CustomStudentDetails(Student student) {
        this.studentId = student.getStudentId();
        this.password = student.getPassword(); // Encoded dateOfBirth used as password
    }

    @Override
    public String getUsername() {
        return studentId; // ✅ Return studentId as username
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No roles for now
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
