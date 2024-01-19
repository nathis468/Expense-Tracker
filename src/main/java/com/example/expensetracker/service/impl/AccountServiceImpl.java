package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Account;
import com.example.expensetracker.repository.AccountRepository;
import com.example.expensetracker.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository theAccountRepository;

    public List<Account> getAllAccount(){
        return theAccountRepository.findAll();
    }

    @Override
    public Account insertNewAccount(Account newAccount) {
        if(newAccount.getBalance()==null){
            newAccount.setBalance(BigDecimal.ZERO);
        }
        theAccountRepository.save(newAccount);
        return newAccount;
    }

}
