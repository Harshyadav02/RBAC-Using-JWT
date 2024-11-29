package com.harsh.JWTLearnings.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.JWTLearnings.Entity.UserEntity;
import com.harsh.JWTLearnings.Service.PublicService;
import com.harsh.JWTLearnings.dto.LoginRequest;

/*  Class contains all public controllers that can be acessed by user without authentication */

@RestController

public class PublicController {


    @Autowired
    PublicService publicService;  


    @PostMapping("/login/")
    public ResponseEntity<?> userLogin(@RequestBody @Validated LoginRequest loginCredentails) {

        try {
            String token = publicService.loginUser(loginCredentails);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/register/")
    public ResponseEntity<?> createUser(@RequestBody @Validated UserEntity empDetails) {

        try {
            return publicService.createUser(empDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
