package com.harsh.JWTLearnings.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.JWTLearnings.Entity.UserEntity;
import com.harsh.JWTLearnings.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    // Utility method to check if a string is not null or empty
    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Utility method to check if the current user has a specific role
    private boolean hasRole(String role) {
        String currentRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        return currentRole.contains(role);
    }

    // Get the profile details of the logged-in user
    public ResponseEntity<?> profile() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity userDetails = userRepo.findByEmail(email);

            if (userDetails != null) {
                return new ResponseEntity<>(userDetails, HttpStatus.OK);
            }
            return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update the details of the logged-in user
    public ResponseEntity<?> updateDetails(UserEntity userDetails) {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity existingUser = userRepo.findByEmail(email);
            System.out.println(existingUser);
            if (existingUser == null) {
                return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
            }

            // Update allowed fields only if they are not empty
            if (isNotEmpty(userDetails.getFirstName())) {
                existingUser.setFirstName(userDetails.getFirstName());
            }
            if (isNotEmpty(userDetails.getLastName())) {
                existingUser.setLastName(userDetails.getLastName());
            }
            if (isNotEmpty(userDetails.getPhoneNumber())) {
                existingUser.setPhoneNumber(userDetails.getPhoneNumber());
            }
            if (isNotEmpty(userDetails.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
            if (isNotEmpty(userDetails.getRole())) {
                existingUser.setRole(userDetails.getRole().toUpperCase());
            }
            userRepo.save(existingUser);

            return new ResponseEntity<>("user details updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Admin-only function to view all employees
    public ResponseEntity<?> getAllUsers() {
        try {
            if (hasRole("ADMIN")) {
                List<UserEntity> userList = (List<UserEntity>) userRepo.findAll();
                return new ResponseEntity<>(userList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Admin-only function to delete an employee by email
    public ResponseEntity<?> deleteUserByEmail(String email) {
        try {
            if (hasRole("ADMIN")) {
                userRepo.deleteByEmail(email);
                return new ResponseEntity<>("User data deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Employee-only function to delete self
    public ResponseEntity<?> deleteSelf() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity employee = userRepo.findByEmail(email);

            if (employee != null && hasRole("EMPLOYEE")) {
                userRepo.deleteByEmail(email);
                SecurityContextHolder.clearContext(); // Clear authentication context after deletion
                return new ResponseEntity<>("User data deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("You are not allowed to delete another employee", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
