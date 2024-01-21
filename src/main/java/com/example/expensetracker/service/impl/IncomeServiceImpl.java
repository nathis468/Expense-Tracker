package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dao.IncomeDAO;
import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.TransactionType;
import com.example.expensetracker.repository.AccountRepository;
import com.example.expensetracker.repository.IncomeRepository;
import com.example.expensetracker.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    private IncomeRepository theIncomeRepository;

    @Autowired
    private AccountRepository theAccountRepository;

    @Autowired
    private IncomeDAO theIncomeDAO;

    @Override
    public List<Income> getAllIncomes() {
        return theIncomeRepository.findAll();
    }

    @Override
    public List<Income> getIncomeByUserName(String userName) {
        return theIncomeRepository.findByUserName(userName);
    }

    @Override
    public Income insertnewIncomeFromAccount(Income newIncome) {
        return theIncomeRepository.save(newIncome);
    }

    @Override
    public Income insertnewIncome(Income newIncome) {
        Income income = theIncomeRepository.save(newIncome);
        String userName=income.getUserName();

        Account accounts=theAccountRepository.findByAccountHolderName(userName);
        
        Transaction list1 = new Transaction();
        list1.setType(TransactionType.INCOME);
        list1.setReferenceId(income.getId());

        BigDecimal previousBalance = BigDecimal.ZERO;
        BigDecimal newIncomeAmount = income.getAmount();
        List<Transaction> list=new ArrayList<>();

        if(accounts.getTransaction()==null){
            list.add(list1);
        }
        else{
            list = accounts.getTransaction();
            list.add(list1);
        }
        previousBalance = accounts.getBalance();
        accounts.setTransaction(list);
        accounts.setBalance(previousBalance.add(newIncomeAmount));
        theAccountRepository.save(accounts);
        income.setPreviousBalance(previousBalance);
        income.setCurrentBalance(previousBalance.add(newIncomeAmount));
        return theIncomeRepository.save(income);
    }

    @Override
    public BigDecimal getTotalIncomeByUserName(String userName) {
        List<Income> list=theIncomeRepository.findAll();
        BigDecimal totalIncome=BigDecimal.ZERO;
        for(Income income:list){
            totalIncome=totalIncome.add(income.getAmount());
        }
        return totalIncome;    
    }

    @Override
    public List<Income> getAllAnualIncome(String userName, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theIncomeDAO.findAnnualIncomeByUserName(userName,startDate,endDate);
    }

    // yearly income from all source - total
    public BigDecimal getAllAnualIncomeTotal(String userName, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        List<Income> list=theIncomeDAO.findAnnualIncomeByUserName(userName,startDate,endDate);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Income income:list){
            totalExpense=totalExpense.add(income.getAmount());
        }
        return totalExpense;
    }

    @Override
    public List<Income> getParticularUsersParticularSourceAnnualIncome(String userName,String source){
        return theIncomeDAO.findIncomeFromParticularSource(userName,source);
    }

    @Override
    public List<Income> getParticularSourceAnnualIncome(String userName,String source,int year){
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theIncomeDAO.findBySourceByDateAndTime(userName,source,startDate,endDate);
    }

    // Annual Income statement from particular source  - total
    @Override
    public BigDecimal getParticularSourceAnnualIncomeTotal(String userName,String source,int year){
        List<Income> list=getParticularSourceAnnualIncome(userName, source, year);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Income income:list){
            totalExpense=totalExpense.add(income.getAmount());
        }
        return totalExpense;
    }

    // Montly Income statement from particular source
    @Override
    public List<Income> getParticularSourceMontlyIncome(String userName,String source,int year,int month){
        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        int dayOfMonth = lastDayOfMonth.getDayOfMonth();
        LocalDateTime startDate=LocalDateTime.of(year, month, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, month, dayOfMonth,23,59,10);
        return theIncomeDAO.findBySourceByDateAndTime(userName,source,startDate,endDate);
    }

    // Montly Income statement from particular source - total
    @Override
    public BigDecimal getParticularSourceMontlyIncomeTotal(String userName,String source,int year,int month){
        List<Income> list=getParticularSourceMontlyIncome(userName,source,year,month);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Income income:list){
            totalExpense=totalExpense.add(income.getAmount());
        }
        return totalExpense;
    }
}
