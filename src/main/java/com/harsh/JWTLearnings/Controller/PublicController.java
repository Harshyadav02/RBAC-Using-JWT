package com.harsh.JWTLearnings.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.JWTLearnings.Entity.Admin;
import com.harsh.JWTLearnings.Entity.Employee;
import com.harsh.JWTLearnings.Service.AdminService;
import com.harsh.JWTLearnings.Service.EmployeeService;

@RestController
public class PublicController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService empService;

    // method to create new admin
    @PostMapping("/admin/signin/")
    public ResponseEntity<?> createAdmin(@RequestBody Admin adminDetails) {

        try {
            return adminService.createAdmin(adminDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/login/")
    public ResponseEntity<?> adminLogin(@RequestBody Admin adminDetails) {
        try {
            String token = adminService.loginUser(adminDetails);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/emp/login/")
    public ResponseEntity<?> empLogin(@RequestBody Employee empDetails) {
        try {
            String token = empService.loginUser(empDetails);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/emp/signin/")
    public ResponseEntity<?> createEmployee(@RequestBody Employee empDetails) {

        try {
            return empService.createEmployee(empDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
