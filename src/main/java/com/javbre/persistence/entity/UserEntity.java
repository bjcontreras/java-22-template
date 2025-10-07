package com.javbre.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    private String identification;

    @Column(name = "identification_type")
    private String identificationType;

    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

