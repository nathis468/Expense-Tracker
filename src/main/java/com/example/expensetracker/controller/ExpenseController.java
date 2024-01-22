package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Expense>> getAllExpensesController(){
        return new ResponseEntity<>(theExpenseService.getAllExpenses(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listall/{userName}")
    public ResponseEntity<List<Expense>> getAllExpensesByUserNameController(@PathVariable("userName") String userName){
        return new ResponseEntity<>(theExpenseService.getExpenseByUserName(userName),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/insertexpense")
    public ResponseEntity<Expense> insertnewExpenseController(@RequestBody Expense newExpense){
        theExpenseService.insertnewExpense(newExpense);
        return new ResponseEntity<>(newExpense,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listtotalexpense/{userName}")
    public ResponseEntity<String> getTotalExpenseController(@PathVariable("userName") String userName){
        BigDecimal res=theExpenseService.getTotalExpenseByUserName(userName);
        return new ResponseEntity<>("Total Expense : "+res,HttpStatus.OK);
    }

    // yearly expense from all categorys
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualexpense/{userName}/{year}")
    public ResponseEntity<List<Expense>> getAnualExpenseController(@PathVariable String userName,@PathVariable int year){
        return new ResponseEntity<>(theExpenseService.getAllAnualExpense(userName,year),HttpStatus.OK);
    }

    // yearly expense from all category - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualexpensetotal/{userName}/{year}")
    public ResponseEntity<String> getMethodName(@PathVariable String userName,@PathVariable int year) {
        BigDecimal res=theExpenseService.getAllAnualExpenseTotal(userName, year);
        return new ResponseEntity<>("Total Expense : "+res,HttpStatus.OK);
    }

    // all expense from particular category
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listuserscategoryexpense/{userName}/{category}")
    public ResponseEntity<List<Expense>> getMethodName(@PathVariable String userName, @PathVariable String category) {
        return new ResponseEntity<>(theExpenseService.getParticularUsersParticularCategoryAnnualExpense(userName,category),HttpStatus.OK);
    }
    
    // Annual Expense statement from particular category   
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualstatement/{userName}/{category}/{year}")
    public ResponseEntity<List<Expense>> getAnnualExpenseStatementController(@PathVariable String userName,@PathVariable String category,@PathVariable int year) {
        return new ResponseEntity<>(theExpenseService.getParticularCategoryAnnualExpense(userName,category,year),HttpStatus.OK);
    }

    // Annual Expense statement from particular category  - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualstatementtotal/{userName}/{category}/{year}")
    public ResponseEntity<String> getAnnualExpenseStatementTotalController(@PathVariable String userName,@PathVariable String category,@PathVariable int year) {
        BigDecimal totalExpense = theExpenseService.getParticularCategoryAnnualExpenseTotal(userName,category,year);
        return new ResponseEntity<>("Total Annual Expense from "+category+" : "+totalExpense,HttpStatus.OK);
    }

    // Montly Expense statement from particular category
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listmonthlystatement/{userName}/{category}/{year}/{month}")
    public ResponseEntity<List<Expense>> getMonthlyExpenseController(@PathVariable String userName,@PathVariable String category,@PathVariable int year,@PathVariable int month) {
        return new ResponseEntity<>(theExpenseService.getParticularCategoryMontlyExpense(userName,category, year, month),HttpStatus.OK);
    }    

    // Montly Expense statement from particular category
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listmonthlystatementtotal/{userName}/{category}/{year}/{month}")
    public ResponseEntity<String> getMonthlyExpenseTotalController(@PathVariable String userName,@PathVariable String category,@PathVariable int year,@PathVariable int month) {
        BigDecimal totalExpense = theExpenseService.getParticularCategoryMontlyExpenseTotal(userName,category, year, month);
        return new ResponseEntity<>("Total Montly("+month+") Expense from "+category+" : "+totalExpense,HttpStatus.OK);
    } 

}
