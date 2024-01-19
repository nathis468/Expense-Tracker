package com.example.expensetracker.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    void insertnewExpense(Expense newExpense);
    String checkExpenseLimit();
    BigDecimal getTotalExpense();
    // List<Expense> getAllExpensesByCategory(User userId); 
    // List<Expense> getAllExpensesByDateAndTime(User userId);
    // List<Expense> getAllExpensesByGivenCategory(User userId,String category);
    List<Expense> getAllAnualExpense(User userId,int year);
    BigDecimal getAllAnualExpenseTotal(User userId, int year);
    List<Expense> getParticularUsersParticularCategoryAnnualExpense(User userId,String category);
    List<Expense> getParticularCategoryAnnualExpense(User userId,String category,int year);
    BigDecimal getParticularCategoryAnnualExpenseTotal(User userId,String category,int year);
    List<Expense> getParticularCategoryMontlyExpense(User userId,String category,int year,int month);
    BigDecimal getParticularCategoryMontlyExpenseTotal(User userId,String category,int year,int month);
}
