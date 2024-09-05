package com.harsh.JWTLearnings.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp/")
public class EmployeeController {

    @GetMapping("/hello/")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String hello() {
        return "Hello employee";
    }
    
}
