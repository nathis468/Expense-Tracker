package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.model.Account;

public interface AccountService {
    List<Account> getAllAccount();
    Account insertNewAccount(Account newAccount);
}
