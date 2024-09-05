package com.harsh.JWTLearnings.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harsh.JWTLearnings.Entity.Admin;

@Repository
public interface AdminRepo extends CrudRepository<Admin,String>{

    public Admin findByEmail(String email);
} 