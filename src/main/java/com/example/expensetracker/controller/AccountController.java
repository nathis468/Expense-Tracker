package com.example.expensetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.Income;
import com.example.expensetracker.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService theAccountService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaccount")
    public List<Account> getListOfAccounts() {
        return theAccountService.getAllAccount();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listaccount/{accountNumber}/{userName}")
    public Account getAccountByAccountNumberController(@PathVariable String accountNumber,@PathVariable String userName) {
        return theAccountService.getByAccountNumberAndUserName(accountNumber,userName);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/addaccount")
    public Account insertNewAccountController(@RequestBody Account newAccount) {
        return theAccountService.insertNewAccount(newAccount);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/insertincome/{accountNumber}")
    public Income insertNewIncomeController(@PathVariable("accountNumber") String accountNumber,@RequestBody Income newIncome){
        theAccountService.insertnewIncome(accountNumber,newIncome);
        return newIncome;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/insertexpense/{accountNumber}")
    public Expense insertNewExpenseController(@PathVariable("accountNumber") String accountNumber,@RequestBody Expense newExpense){
        theAccountService.insertnewExpense(accountNumber,newExpense);
        return newExpense;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/deleteaccount/{accountNumber}")
    public String deleteAccountController(@PathVariable String accountNumber){
        theAccountService.deleteAccount(accountNumber);
        return "Deleted Account Successfully : "+accountNumber;
    }
}
