package com.example.expensetracker.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    String insertnewExpense(Expense newExpense);
    String checkExpenseLimit();
    BigDecimal getTotalExpense();
    List<Expense> getAllExpensesByCategory(User userId);
    List<Expense> getAllExpensesByDateAndTime(User userId);
    List<Expense> getAllExpensesByGivenCategory(User userId,String category);
}
