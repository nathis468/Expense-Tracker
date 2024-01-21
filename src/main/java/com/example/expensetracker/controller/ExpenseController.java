package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listall")
    public List<Expense> getAllExpensesController(){
        return theExpenseService.getAllExpenses();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listall/{userName}")
    public List<Expense> getAllExpensesByUserNameController(@PathVariable("userName") String userName){
        return theExpenseService.getExpenseByUserName(userName);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/insertexpense")
    public Expense insertnewExpenseController(@RequestBody Expense newExpense){
        theExpenseService.insertnewExpense(newExpense);
        return newExpense;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listtotalexpense/{userName}")
    public String getTotalExpenseController(@PathVariable("userName") String userName){
        BigDecimal res=theExpenseService.getTotalExpenseByUserName(userName);
        return "Total Expense : "+res;
    }

    // yearly expense from all categorys
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualexpense/{userName}/{year}")
    public List<Expense> getAnualExpenseController(@PathVariable String userName,@PathVariable int year){
        return theExpenseService.getAllAnualExpense(userName,year);
    }

    // yearly expense from all category - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualexpensetotal/{userName}/{year}")
    public String getMethodName(@PathVariable String userName,@PathVariable int year) {
        BigDecimal res=theExpenseService.getAllAnualExpenseTotal(userName, year);
        return "Total Expense : "+res;
    }

    // all expense from particular category
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listuserscategoryexpense/{userName}/{category}")
    public List<Expense> getMethodName(@PathVariable String userName, @PathVariable String category) {
        return theExpenseService.getParticularUsersParticularCategoryAnnualExpense(userName,category);
    }
    
    // Annual Expense statement from particular category   
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualstatement/{userName}/{category}/{year}")
    public List<Expense> getAnnualExpenseStatementController(@PathVariable String userName,@PathVariable String category,@PathVariable int year) {
        return theExpenseService.getParticularCategoryAnnualExpense(userName,category,year);
    }

    // Annual Expense statement from particular category  - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualstatementtotal/{userName}/{category}/{year}")
    public String getAnnualExpenseStatementTotalController(@PathVariable String userName,@PathVariable String category,@PathVariable int year) {
        BigDecimal totalExpense = theExpenseService.getParticularCategoryAnnualExpenseTotal(userName,category,year);
        return "Total Annual Expense from "+category+" : "+totalExpense;
    }

    // Montly Expense statement from particular category
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listmonthlystatement/{userName}/{category}/{year}/{month}")
    public List<Expense> getMonthlyExpenseController(@PathVariable String userName,@PathVariable String category,@PathVariable int year,@PathVariable int month) {
        return theExpenseService.getParticularCategoryMontlyExpense(userName,category, year, month);
    }    

    // Montly Expense statement from particular category
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listmonthlystatementtotal/{userName}/{category}/{year}/{month}")
    public String getMonthlyExpenseTotalController(@PathVariable String userName,@PathVariable String category,@PathVariable int year,@PathVariable int month) {
        BigDecimal totalExpense = theExpenseService.getParticularCategoryMontlyExpenseTotal(userName,category, year, month);
        return "Total Montly("+month+") Expense from "+category+" : "+totalExpense;
    } 

}
