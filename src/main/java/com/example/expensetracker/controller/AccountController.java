package com.example.expensetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.Income;
import com.example.expensetracker.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getListOfAccounts() {
        return new ResponseEntity<>(theAccountService.getAllAccount(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listaccount/{accountNumber}/{userName}")
    public ResponseEntity<?> getAccountByAccountNumberController(@PathVariable String accountNumber,@PathVariable String userName) {
        return new ResponseEntity<>(theAccountService.getByAccountNumberAndUserName(accountNumber,userName),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/addaccount")
    public ResponseEntity<?> insertNewAccountController(@RequestBody Account newAccount) {
        return new ResponseEntity<>(theAccountService.insertNewAccount(newAccount),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/insertincome/{accountNumber}")
    public ResponseEntity<Income> insertNewIncomeController(@PathVariable("accountNumber") String accountNumber,@RequestBody Income newIncome){
        theAccountService.insertnewIncome(accountNumber,newIncome);
        return new ResponseEntity<>(newIncome,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/insertexpense/{accountNumber}")
    public ResponseEntity<Expense> insertNewExpenseController(@PathVariable("accountNumber") String accountNumber,@RequestBody Expense newExpense){
        theAccountService.insertnewExpense(accountNumber,newExpense);
        return new ResponseEntity<>(newExpense,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/deleteaccount/{accountNumber}")
    public ResponseEntity<String> deleteAccountController(@PathVariable String accountNumber){
        theAccountService.deleteAccount(accountNumber);
        return new ResponseEntity<>("Deleted Account Successfully : "+accountNumber,HttpStatus.OK);
    }
}
