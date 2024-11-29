package com.harsh.JWTLearnings.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/* This class is the actual database entity which holds user details */
@Entity
@Data
public class UserEntity {

    @Id
    @Column(nullable = false,unique = true)

    @NotNull(message = "email cannot be null")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "password cannot be null")
    private String password;
    @Column(nullable = false)
    @NotNull(message = "firstName cannot be null")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "lastName cannot be null")
    private String lastName;
    
    @Column(nullable = false)
    @Size(min=10,max=10)
    @NotNull(message = "phoneNumber cannot be null")
    private String phoneNumber;

    @Column(nullable = false)
    @NotNull(message = "position cannot be null")
    private String position;

    @Column(nullable = false)
    @NotNull(message = "role cannot be null")
    private String  role;
}
