package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;


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
    public ResponseEntity<?> insertnewExpenseController(@RequestBody Expense newExpense){
        if(theExpenseService.insertnewExpense(newExpense)==null){
            return new ResponseEntity<>("Inserted new Expense",HttpStatus.OK);
        }
        return new ResponseEntity<>("You have exceeded your Budget",HttpStatus.OK);
    }

    @GetMapping("/listtotalexpense")
    public String getTotalExpenseController(){
        BigDecimal res=theExpenseService.getTotalExpense();
        return "Total Expense : "+res;
    }

    @GetMapping("/listbyid/{userId}")
    public List<Expense> getByUserIdController(@PathVariable User userId) {
        return theExpenseService.getAllExpensesByCategory(userId);
    }

    @GetMapping("/listbydateandtime/{userId}")
    public List<Expense> getByUserIdBasedOnTimeController(@PathVariable User userId) {
        return theExpenseService.getAllExpensesByDateAndTime(userId);
    }

    @GetMapping("/listbyid/{userId}/{category}")
    public List<Expense> getByUserIdController(@PathVariable User userId,@PathVariable String category) {
        return theExpenseService.getAllExpensesByGivenCategory(userId,category);
    }
    
}
