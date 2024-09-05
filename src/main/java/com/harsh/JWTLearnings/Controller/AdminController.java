package com.harsh.JWTLearnings.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AdminController {

    @GetMapping("/hello/")
    public ResponseEntity<?> hello(){
        try {
            return new ResponseEntity<>("Hello admin",HttpStatus.OK);
        } catch (Exception e) {
             return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
