package com.harsh.JWTLearnings.Repository;

import org.springframework.data.repository.CrudRepository;

import com.harsh.JWTLearnings.Entity.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, String>{
    public Employee findByEmail(String email);
}
