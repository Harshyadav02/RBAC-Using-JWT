package com.harsh.JWTLearnings.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.JWTLearnings.Entity.Admin;
import com.harsh.JWTLearnings.Service.AdminService;

@RestController
public class PublicController {
    @Autowired
    private AdminService adminService;

    // method to create new admin
    @PostMapping("/signin/")
    public ResponseEntity<?> signin(@RequestBody Admin adminDetails){

        try {
            return adminService.createAdmin(adminDetails);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login/")
    public ResponseEntity<String> login(@RequestBody Admin adminDetails) {
        
        try {
            return adminService.loginUser(adminDetails);

        } catch (Exception e) {
           return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
