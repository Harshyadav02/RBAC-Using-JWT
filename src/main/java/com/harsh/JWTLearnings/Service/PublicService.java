package com.harsh.JWTLearnings.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.JWTLearnings.Entity.UserEntity;
import com.harsh.JWTLearnings.Repository.UserRepo;
import com.harsh.JWTLearnings.Security.JWTUtils;
import com.harsh.JWTLearnings.Security.UserDetailsServiceImp;
import com.harsh.JWTLearnings.dto.LoginRequest;

/* This class holds the logic for public route */
@Service
public class PublicService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    private JWTUtils jwtUtils;

    // login method that genrate a JWT token after user credentails validation
    
    public String loginUser(LoginRequest loginRequest) {
        try {

            // Load user details using the email 
            UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(loginRequest.getEmail());

            // Check if the entered password matches the stored password
            if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
               
                return jwtUtils.generateTokenFromUsername(userDetails);
            } else {
                
                return "Invalid password!";
            }
        } catch (Exception e) {
           
            return e.getLocalizedMessage();
        }
    }

    // Method that create a user and save to db
    public ResponseEntity<?> createUser(UserEntity userDetails) {
        try {
            String email = userDetails.getEmail();
            if (userRepo.findByEmail(email) != null) {
                throw new Exception("Email already exist");
            }
            userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            userRepo.save(userDetails);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
