package com.harsh.JWTLearnings.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harsh.JWTLearnings.Entity.UserEntity;
@Repository
public interface UserRepo extends CrudRepository<UserEntity, String>{
    public UserEntity findByEmail(String email);
    
    public void deleteByEmail(String email);
}
