package com.harsh.JWTLearnings.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harsh.JWTLearnings.Entity.Admin;
import com.harsh.JWTLearnings.Entity.Employee;
import com.harsh.JWTLearnings.Repository.AdminRepo;
import com.harsh.JWTLearnings.Repository.EmployeeRepo;

/**
 * Custom UserDetailsService implementation to load user details from the database.
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Admin adminDetail = adminRepo.findByEmail(email);
        if (adminDetail != null) {
            // Return UserDetails object with admin details
            return User.builder()
                       .username(adminDetail.getEmail())
                       .password(adminDetail.getPassword())
                       .roles(adminDetail.getRole())  // Roles/authorities
                       .build();
        }
        // Find employee by email
        Employee employeeDetail = employeeRepo.findByEmail(email);
        if (employeeDetail != null) {
            // Return UserDetails object with employee details
            return User.builder()
                       .username(employeeDetail.getEmail())
                       .password(employeeDetail.getPassword())
                       .roles(employeeDetail.getRole())  // Roles/authorities
                       .build();
        }

        // Throw exception if user not found
        throw new UsernameNotFoundException("No user found with email: " + email);
    }
}
