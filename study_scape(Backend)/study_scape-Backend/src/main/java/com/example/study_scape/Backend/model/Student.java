package com.example.study_scape.Backend.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private String studentName;

    @Column(unique = true, nullable = false)
    private String studentId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String collegeRoll;

    @Column(nullable = false)
    private String courseName;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private String password;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "role_uuid", nullable = false)
//    private Role role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void generateUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    // Constructor to initialize with UUID from String
    public Student(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }
}
