package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.TransactionType;
import com.example.expensetracker.repository.AccountRepository;
import com.example.expensetracker.service.AccountService;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.IncomeService;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository theAccountRepository;

    @Autowired
    private IncomeService theIncomeService;

    @Autowired
    private ExpenseService theExpenseService;

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

    @Override
    public Account insertnewIncome(String accountNumber,Income newIncome) {
        Income income=theIncomeService.insertnewIncomeFromAccount(newIncome);

        Account account = theAccountRepository.findByAccountNumber(accountNumber);
        
        Transaction list1=new Transaction();
        list1.setType(TransactionType.INCOME);
        list1.setReferenceId(income.getId());

        List<Transaction> list=new ArrayList<>();

        if(account.getTransaction()==null){
            list.add(list1);
        }
        else{
            list = account.getTransaction();
            list.add(list1);
        }
        income.setPreviousBalance(account.getBalance());
        
        account.setTransaction(list);
        account.setBalance(account.getBalance().add(income.getAmount()));
        theAccountRepository.save(account);
        
        income.setCurrentBalance(account.getBalance());
        theIncomeService.insertnewIncomeFromAccount(income);
        
        return account;
    }
    
    @Override
    public Account insertnewExpense(String accountNumber, Expense newExpense) {
        Expense expense=theExpenseService.insertnewExpenseFromAccount(newExpense);

        Account account = theAccountRepository.findByAccountNumber(accountNumber);
        
        Transaction list1=new Transaction();
        list1.setType(TransactionType.EXPENSE);
        list1.setReferenceId(expense.getId());

        List<Transaction> list=new ArrayList<>();

        if(account.getTransaction()==null){
            list.add(list1);
        }
        else{
            list = account.getTransaction();
            list.add(list1);
        }
        expense.setPreviousBalance(account.getBalance());
        
        account.setTransaction(list);
        account.setBalance(account.getBalance().subtract(expense.getAmount()));
        theAccountRepository.save(account);
        
        expense.setCurrentBalance(account.getBalance());
        theExpenseService.insertnewExpenseFromAccount(expense);
        
        return account;    
    }

    @Override
    public void deleteAccount(String accountNumber) {
        theAccountRepository.deleteByAccountNumber(accountNumber);
    }

    @Override
    public Account getByAccountNumberAndUserName(String accountNumber, String userName) {
        return theAccountRepository.findByAccountNumberAndAccountHolderName(accountNumber, userName);
    }
}
