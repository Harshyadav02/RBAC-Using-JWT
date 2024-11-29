package com.harsh.JWTLearnings.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.JWTLearnings.Entity.UserEntity;
import com.harsh.JWTLearnings.Service.UserService;

@RestController

public class UserController {

    @Autowired
    private UserService userService;  

    // Endpoint to get the profile details of the logged-in user
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return userService.profile();
    }

    // Endpoint to update the details of the logged-in user
    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UserEntity userDetails) {
        return userService.updateDetails(userDetails);
    }

    // Admin-only endpoint to view all employees
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    // Admin-only endpoint to delete an employee by email
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String email) {
        return userService.deleteUserByEmail(email);
    }

    // Employee-only endpoint to delete self
    @DeleteMapping("/delete-self")
    public ResponseEntity<?> deleteSelf() {
        return userService.deleteSelf();
    }
}
