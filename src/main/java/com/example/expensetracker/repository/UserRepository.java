package com.example.expensetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.expensetracker.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
    
}
