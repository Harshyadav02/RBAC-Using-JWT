package com.harsh.JWTLearnings.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.harsh.JWTLearnings.Entity.Admin;
import com.harsh.JWTLearnings.Repository.AdminRepo;

public class AdminService {
    /* Injecting admin repo and password encoder */
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public ResponseEntity<?> createAdmin(Admin adminDetails) {
        
        // encrytping password
        adminDetails.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        adminRepo.save(adminDetails);

        return new ResponseEntity<>("Admin created sucessfully",HttpStatus.OK);
    }
    
}
