package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository theExpenseRepository;

    @Override
    public List<Expense> getAllExpenses(){
        return theExpenseRepository.findAll();
    }

    @Override
    public void insertnewExpense(Expense newExpense){
        theExpenseRepository.save(newExpense);
    }

    @Override
    public BigDecimal getTotalExpense(){
        List<Expense> list=theExpenseRepository.findAll();
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;
    }
}
