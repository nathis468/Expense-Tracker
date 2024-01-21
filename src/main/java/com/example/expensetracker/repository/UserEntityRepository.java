package com.example.expensetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.expensetracker.model.User;
import com.example.expensetracker.model.UserEntity;

public interface UserEntityRepository extends MongoRepository<UserEntity,String>{
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
}
