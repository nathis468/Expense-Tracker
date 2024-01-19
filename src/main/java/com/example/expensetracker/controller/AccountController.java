package com.example.expensetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Account;
import com.example.expensetracker.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService theAccountService;

    @GetMapping("/listaccount")
    public List<Account> getListOfAccounts() {
        return theAccountService.getAllAccount();
    }

    // @GetMapping("/listaccount/{userId}")
    // public Account getListOfAccounts() {
    //     return new SomeData();
    // }    

    @PostMapping("/addaccount")
    public Account postMethodName(@RequestBody Account newAccount) {
        return theAccountService.insertNewAccount(newAccount);
    }
    
    
}
