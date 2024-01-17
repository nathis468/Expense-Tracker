package com.example.expensetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.expensetracker.model.User;

public interface UserRepository extends MongoRepository<User,String>{
    
}
