package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.Income;

public interface AccountService {
    List<Account> getAllAccount();
    Account insertNewAccount(Account newAccount);
    Account insertnewIncome(String accountNumber,Income newIncome);
    Account insertnewExpense(String accountNumber,Expense newExpense);
    void deleteAccount(String accountNumber);
    Account getByAccountNumberAndUserName(String accountNumber,String userName);
}
