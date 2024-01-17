package com.example.expensetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.expensetracker.model.Expense;

public interface ExpenseRepository extends MongoRepository<Expense,String>{
    
}
