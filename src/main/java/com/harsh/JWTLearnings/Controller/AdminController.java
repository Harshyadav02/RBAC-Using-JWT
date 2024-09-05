package com.harsh.JWTLearnings.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping("/hello/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> hello(){
        try {
            return new ResponseEntity<>("Hello admin",HttpStatus.OK);
        } catch (Exception e) {
             return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
