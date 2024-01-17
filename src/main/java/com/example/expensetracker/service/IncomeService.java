package com.example.expensetracker.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.expensetracker.model.Income;

public interface IncomeService {
    List<Income> getAllIncomes();
    void insertnewIncome(Income newIncome);
    BigDecimal getTotalIncome();
}
