package com.example.expensetracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;



public interface ExpenseRepository extends MongoRepository<Expense,String>{
    List<Expense> findByUserId(User userId);
    List<Expense> findByUserIdOrderByCategoryAsc(User userId);
    List<Expense> findByUserIdOrderByDateAndTimeDesc(User userId);
    List<Expense> findByUserIdAndCategoryOrderByDateAndTimeDesc(User userId,String description);
}
