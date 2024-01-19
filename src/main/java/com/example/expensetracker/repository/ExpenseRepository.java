package com.example.expensetracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.User;



public interface ExpenseRepository extends MongoRepository<Expense,String>{
    List<Expense> findByUserId(User userId);
    List<Expense> findByUserIdOrderByCategoryAsc(User userId);
    List<Expense> findByUserIdOrderByDateAndTimeDesc(User userId);
    List<Expense> findByUserIdAndCategoryOrderByDateAndTimeDesc(User userId,String description);

    List<Income> findByCategory(String category);

    @Query("'dateAndTime': {$gte : startDate,lte : endDate}")
    List<Income> findByDateAndTime(LocalDateTime startDate, LocalDateTime endDate);

    @Query("{'category': category}, 'dateAndTime': {$gte : startDate,lte : endDate}")
    List<Income> findByCategoryByDateAndTime(String category,LocalDateTime startDate,LocalDateTime endDate);

}
