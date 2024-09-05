package com.harsh.JWTLearnings.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {
    @Id
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private final String role = "ADMIN";
}
