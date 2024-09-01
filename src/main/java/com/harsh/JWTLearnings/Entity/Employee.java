package com.harsh.JWTLearnings.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    
    private String password;
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    @Size(min=10,max=10)
    private String phoneNumber;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String  role = "EMPLOYEE";
}
