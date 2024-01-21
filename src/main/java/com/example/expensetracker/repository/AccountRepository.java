package com.example.expensetracker.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.expensetracker.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account,String>{
    Account findByAccountHolderName(String username);
    Account findByAccountNumberAndAccountHolderName(String accountNumber,String userName);
    void deleteByAccountNumber(String accountNumber);
    Account findByAccountNumber(String accountNumber);
}
