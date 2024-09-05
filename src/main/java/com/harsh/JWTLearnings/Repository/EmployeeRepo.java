package com.harsh.JWTLearnings.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harsh.JWTLearnings.Entity.Employee;
@Repository
public interface EmployeeRepo extends CrudRepository<Employee, String>{
    public Employee findByEmail(String email);
}
