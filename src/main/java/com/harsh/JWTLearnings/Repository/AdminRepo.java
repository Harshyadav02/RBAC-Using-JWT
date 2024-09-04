package com.harsh.JWTLearnings.Repository;

import org.springframework.data.repository.CrudRepository;

import com.harsh.JWTLearnings.Entity.Admin;

public interface AdminRepo extends CrudRepository<Admin,String>{

    public Admin findByEmail(String email);
} 