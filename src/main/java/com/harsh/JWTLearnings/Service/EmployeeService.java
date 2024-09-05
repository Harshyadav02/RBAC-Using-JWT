package com.harsh.JWTLearnings.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harsh.JWTLearnings.Entity.Employee;
import com.harsh.JWTLearnings.Repository.EmployeeRepo;
import com.harsh.JWTLearnings.Security.JWTUtils;
import com.harsh.JWTLearnings.Security.UserDetailsServiceImp;

@Service
public class EmployeeService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    private JWTUtils jwtUtils;

    public String loginUser(Employee empDetails) {
        try {
          
            return jwtUtils
                    .generateTokenFromUsername(userDetailsServiceImp
                            .loadUserByUsername(empDetails.getEmail()));
        } catch (Exception e) {

            return e.getLocalizedMessage();
        }

    }

    public ResponseEntity<?> createEmployee(Employee empDetails){
        try{
            empDetails.setPassword(passwordEncoder.encode(empDetails.getPassword()));
            employeeRepo.save(empDetails);
            return new ResponseEntity<>("Employee created successfully",HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

}
