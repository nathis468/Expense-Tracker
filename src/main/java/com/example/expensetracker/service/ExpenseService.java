package com.example.expensetracker.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.expensetracker.model.Expense;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    List<Expense> getExpenseByUserName(String userName);
    Expense insertnewExpense(Expense newExpense);
    Expense insertnewExpenseFromAccount(Expense newExpense);
    // String checkExpenseLimit();
    BigDecimal getTotalExpenseByUserName(String userName);
    List<Expense> getAllAnualExpense(String userName,int year);
    BigDecimal getAllAnualExpenseTotal(String userName, int year);
    List<Expense> getParticularUsersParticularCategoryAnnualExpense(String userName,String category);
    List<Expense> getParticularCategoryAnnualExpense(String userName,String category,int year);
    BigDecimal getParticularCategoryAnnualExpenseTotal(String userName,String category,int year);
    List<Expense> getParticularCategoryMontlyExpense(String userName,String category,int year,int month);
    BigDecimal getParticularCategoryMontlyExpenseTotal(String userName,String category,int year,int month);
}
