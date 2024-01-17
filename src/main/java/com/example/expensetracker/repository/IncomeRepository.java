package com.example.expensetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.expensetracker.model.Income;

public interface IncomeRepository extends MongoRepository<Income,String>{
    
}
