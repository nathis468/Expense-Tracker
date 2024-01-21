package com.example.expensetracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.expensetracker.model.Income;

@Repository
public interface IncomeRepository extends MongoRepository<Income,String>{
    List<Income> findByUserName(String userName);
}