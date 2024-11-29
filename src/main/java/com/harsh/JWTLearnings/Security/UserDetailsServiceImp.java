package com.harsh.JWTLearnings.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.harsh.JWTLearnings.Entity.UserEntity;
import com.harsh.JWTLearnings.Repository.UserRepo;

/**
 * Custom UserDetailsService implementation to load user details from the database.
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        // Find employee by email
        UserEntity userDetails = userRepo.findByEmail(email);
        
        if (userDetails != null) {
            // Return UserDetails object with employee details
            return User.builder()
                       .username(userDetails.getEmail())
                       .password(userDetails.getPassword())
                       .roles(userDetails.getRole())  // Roles/authorities
                       .build();
        }

        // Throw exception if user not found
        throw new UsernameNotFoundException("No user found with email: " + email);
    }
}
