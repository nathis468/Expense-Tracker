package com.example.expensetracker.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.expensetracker.model.Expense;

public interface ExpenseService {
    List<Expense> getAllExpenses();
    void insertnewExpense(Expense newExpense);
    BigDecimal getTotalExpense();
}
