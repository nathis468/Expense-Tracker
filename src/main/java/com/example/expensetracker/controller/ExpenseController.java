package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Expense insertnewExpenseController(@RequestBody Expense newExpense){
        theExpenseService.insertnewExpense(newExpense);
        return newExpense;
    }

    @GetMapping("/listtotalexpense")
    public String getTotalExpenseController(){
        BigDecimal res=theExpenseService.getTotalExpense();
        return "Total Expense : "+res;
    }

    // @GetMapping("/listbyid/{userId}")
    // public List<Expense> getByUserIdController(@PathVariable User userId) {
    //     return theExpenseService.getAllExpensesByCategory(userId);
    // }

    // @GetMapping("/listbydateandtime/{userId}")
    // public List<Expense> getByUserIdBasedOnTimeController(@PathVariable User userId) {
    //     return theExpenseService.getAllExpensesByDateAndTime(userId);
    // }

    // @GetMapping("/listbyid/{userId}/{category}")
    // public List<Expense> getByUserIdController(@PathVariable User userId,@PathVariable String category) {
    //     return theExpenseService.getAllExpensesByGivenCategory(userId,category);
    // }


    // yearly expense from all categorys
    @GetMapping("/listannualexpense/{userId}/{year}")
    public List<Expense> getAnualExpenseController(@PathVariable User userId,@PathVariable int year){
        return theExpenseService.getAllAnualExpense(userId,year);
    }

    // yearly expense from all category - total
    @GetMapping("/listannualexpensetotal/{userId}/{year}")
    public String getMethodName(@PathVariable User userId,@PathVariable int year) {
        BigDecimal res=theExpenseService.getAllAnualExpenseTotal(userId, year);
        return "Total Expense : "+res;
    }

    // all expense from particular category
    @GetMapping("/listuserscategoryexpense/{userId}/{category}")
    public List<Expense> getMethodName(@PathVariable User userId, @PathVariable String category) {
        return theExpenseService.getParticularUsersParticularCategoryAnnualExpense(userId,category);
    }
    
    // Annual Expense statement from particular category   
    @GetMapping("/listannualstatement/{userId}/{category}/{year}")
    public List<Expense> getAnnualExpenseStatementController(@PathVariable User userId,@PathVariable String category,@PathVariable int year) {
        return theExpenseService.getParticularCategoryAnnualExpense(userId,category,year);
    }

    // Annual Expense statement from particular category  - total
    @GetMapping("/listannualstatementtotal/{userId}/{category}/{year}")
    public String getAnnualExpenseStatementTotalController(@PathVariable User userId,@PathVariable String category,@PathVariable int year) {
        BigDecimal totalExpense = theExpenseService.getParticularCategoryAnnualExpenseTotal(userId,category,year);
        return "Total Annual Expense from "+category+" : "+totalExpense;
    }

    // Montly Expense statement from particular category
    @GetMapping("/listmonthlystatement/{userId}/{category}/{year}/{month}")
    public List<Expense> getMonthlyExpenseController(@PathVariable User userId,@PathVariable String category,@PathVariable int year,@PathVariable int month) {
        return theExpenseService.getParticularCategoryMontlyExpense(userId,category, year, month);
    }    

    // Montly Expense statement from particular category
    @GetMapping("/listmonthlystatementtotal/{userId}/{category}/{year}/{month}")
    public String getMonthlyExpenseTotalController(@PathVariable User userId,@PathVariable String category,@PathVariable int year,@PathVariable int month) {
        BigDecimal totalExpense = theExpenseService.getParticularCategoryMontlyExpenseTotal(userId,category, year, month);
        return "Total Montly("+month+") Expense from "+category+" : "+totalExpense;
    } 

}
