package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dao.IncomeDAO;
import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.TransactionType;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.AccountRepository;
import com.example.expensetracker.repository.IncomeRepository;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    private IncomeRepository theIncomeRepository;

    @Autowired
    private AccountRepository theAccountRepository;

    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private IncomeDAO theIncomeDAO;

    @Override
    public List<Income> getAllIncomes() {
        return theIncomeRepository.findAll();
    }

    @Override
    public void insertnewIncome(Income newIncome) {
        Income income = theIncomeRepository.save(newIncome);
        String user1=income.getUserId().getId().toString();
        Optional<User> listuser=theUserRepository.findById(user1);
        if(listuser == null) return;
        String username=listuser.get().getUsername();

        Account accounts=theAccountRepository.findByAccountHolderName(username);
        
        Transaction list1 = new Transaction();
        list1.setType(TransactionType.INCOME);
        list1.setReferenceId(income.getId());

        BigDecimal previousBalance = BigDecimal.ZERO;
        BigDecimal newIncomeAmount = income.getAmount();
        // if(username.equals(accounts.getAccountHolderName())){
            List<Transaction> list=new ArrayList<>();

            if(accounts.getTransaction()==null){
                list.add(list1);
            }
            else{
                list = accounts.getTransaction();
                list.add(list1);
            }
            previousBalance = accounts.getBalance();
            System.out.println(previousBalance);
            accounts.setTransaction(list);
            accounts.setBalance(previousBalance.add(newIncomeAmount));
            theAccountRepository.save(accounts);
            income.setPreviousBalance(previousBalance);
            income.setCurrentBalance(previousBalance.add(newIncomeAmount));
            theIncomeRepository.save(income);
        // }
    }

    @Override
    public BigDecimal getTotalIncome() {
        List<Income> list=theIncomeRepository.findAll();
        BigDecimal totalIncome=BigDecimal.ZERO;
        for(Income income:list){
            totalIncome=totalIncome.add(income.getAmount());
        }
        return totalIncome;    
    }

    @Override
    public List<Income> getAllAnualIncome(User userId, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theIncomeDAO.findAnnualIncomeByUserId(userId,startDate,endDate);
    }

    // yearly income from all source - total
    public BigDecimal getAllAnualIncomeTotal(User userId, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        List<Income> list=theIncomeDAO.findAnnualIncomeByUserId(userId,startDate,endDate);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Income income:list){
            totalExpense=totalExpense.add(income.getAmount());
        }
        return totalExpense;
    }

    @Override
    public List<Income> getParticularUsersParticularSourceAnnualIncome(User userId,String source){
        return theIncomeDAO.findIncomeFromParticularSource(userId,source);
    }

    @Override
    public List<Income> getParticularSourceAnnualIncome(User userId,String source,int year){
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theIncomeDAO.findBySourceByDateAndTime(userId,source,startDate,endDate);
    }

    // Annual Income statement from particular source  - total
    @Override
    public BigDecimal getParticularSourceAnnualIncomeTotal(User userId,String source,int year){
        List<Income> list=getParticularSourceAnnualIncome(userId, source, year);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Income income:list){
            totalExpense=totalExpense.add(income.getAmount());
        }
        return totalExpense;
    }

    // Montly Income statement from particular source
    @Override
    public List<Income> getParticularSourceMontlyIncome(User userId,String source,int year,int month){
        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        int dayOfMonth = lastDayOfMonth.getDayOfMonth();
        LocalDateTime startDate=LocalDateTime.of(year, month, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, month, dayOfMonth,23,59,10);
        return theIncomeDAO.findBySourceByDateAndTime(userId,source,startDate,endDate);
    }

    // Montly Income statement from particular source - total
    @Override
    public BigDecimal getParticularSourceMontlyIncomeTotal(User userId,String source,int year,int month){
        List<Income> list=getParticularSourceMontlyIncome(userId,source,year,month);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Income income:list){
            totalExpense=totalExpense.add(income.getAmount());
        }
        return totalExpense;
    }
}
