package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
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
    public String insertnewExpense(Expense newExpense){
        theExpenseRepository.save(newExpense);
        return checkExpenseLimit();
    }

    @Override
    public String checkExpenseLimit(){

        BigDecimal budget=BigDecimal.valueOf(5000);
        if(getTotalExpense().compareTo(budget)>0){
            return "You have reached your budget";
        }
        return null;
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

    @Override
    public List<Expense> getAllExpensesByCategory(User userId) {
        return theExpenseRepository.findByUserIdOrderByCategoryAsc(userId);
    }

    @Override
    public List<Expense> getAllExpensesByDateAndTime(User userId){
        return theExpenseRepository.findByUserIdOrderByDateAndTimeDesc(userId);
    }

    @Override
    public List<Expense> getAllExpensesByGivenCategory(User userId,String category) {
        return theExpenseRepository.findByUserIdAndCategoryOrderByDateAndTimeDesc(userId,category);
    }    
}
