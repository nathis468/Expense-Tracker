package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.model.Expense;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService theExpenseService;

    @GetMapping("/listall")
    public List<Expense> getAllExpensesController(){
        return theExpenseService.getAllExpenses();
    }

    @PostMapping("/insertexpense")
    public Expense insertnewExpenseController(@RequestBody Expense newExpense){
        theExpenseService.insertnewExpense(newExpense);
        return newExpense;
    }

    @GetMapping("/listtotalexpense")
    public String getTotalExpenseController(){
        BigDecimal res=theExpenseService.getTotalExpense();
        return "Total Expense : "+res;
    }
}
