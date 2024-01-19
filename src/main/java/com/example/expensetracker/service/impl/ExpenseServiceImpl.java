package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dao.ExpenseDAO;
import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.TransactionType;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.AccountRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository theExpenseRepository;

    @Autowired
    private AccountRepository theAccountRepository;

    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private ExpenseDAO theExpenseDAO;

    @Override
    public List<Expense> getAllExpenses(){
        return theExpenseRepository.findAll();
    }

    // @Override
    // public String insertnewExpense(Expense newExpense){
    //     theExpenseRepository.save(newExpense);
    //     return checkExpenseLimit();
    // }

    @Override
    public String checkExpenseLimit(){

        BigDecimal budget=BigDecimal.valueOf(5000);
        if(getTotalExpense().compareTo(budget)>0){
            return "You have reached your budget";
        }
        return null;
    }

    // @Override
    // public BigDecimal getTotalExpense(){
    //     List<Expense> list=theExpenseRepository.findAll();
    //     BigDecimal totalExpense=BigDecimal.ZERO;
    //     for(Expense expense:list){
    //         totalExpense=totalExpense.add(expense.getAmount());
    //     }
    //     return totalExpense;
    // }

    // @Override
    // public List<Expense> getAllExpensesByCategory(User userId) {
    //     return theExpenseRepository.findByUserIdOrderByCategoryAsc(userId);
    // }

    // @Override
    // public List<Expense> getAllExpensesByDateAndTime(User userId){
    //     return theExpenseRepository.findByUserIdOrderByDateAndTimeDesc(userId);
    // }

    // @Override
    // public List<Expense> getAllExpensesByGivenCategory(User userId,String category) {
    //     return theExpenseRepository.findByUserIdAndCategoryOrderByDateAndTimeDesc(userId,category);
    // }    


    @Override
    public void insertnewExpense(Expense newExpense) {
        String userId = newExpense.getUserId().getId().toString();
        Optional<User> listuser = theUserRepository.findById(userId);
        if(listuser == null) return;
        String username=listuser.get().getUsername();
        
        Account accounts=theAccountRepository.findByAccountHolderName(username);
        
        BigDecimal currentBalance = accounts.getBalance();
        BigDecimal expenseAmount = newExpense.getAmount();

        if(currentBalance.compareTo(expenseAmount)==1 || currentBalance.compareTo(expenseAmount)==0){
            Expense expense = theExpenseRepository.save(newExpense);
            expense.setPreviousBalance(accounts.getBalance());
            expense.setCurrentBalance(currentBalance.subtract(expense.getAmount()));
            accounts.setBalance(currentBalance.subtract(expense.getAmount()));

            Transaction list1 = new Transaction();
            list1.setType(TransactionType.EXPENSE);
            list1.setReferenceId(expense.getId().toString());

            List<Transaction> list=new ArrayList<>();
            if(accounts.getTransaction()==null){
                list.add(list1);
            }
            else{
                list = accounts.getTransaction();
                list.add(list1);
            }
            accounts.setTransaction(list);

            theAccountRepository.save(accounts);
            theExpenseRepository.save(expense);
        }
    }

    @Override
    public BigDecimal getTotalExpense() {
        List<Expense> list=theExpenseRepository.findAll();
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;    
    }

    @Override
    public List<Expense> getAllAnualExpense(User userId, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theExpenseDAO.findAnnualExpenseByUserId(userId,startDate,endDate);
    }

    // yearly expense from all category - total
    public BigDecimal getAllAnualExpenseTotal(User userId, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        List<Expense> list=theExpenseDAO.findAnnualExpenseByUserId(userId,startDate,endDate);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;
    }

    @Override
    public List<Expense> getParticularUsersParticularCategoryAnnualExpense(User userId,String category){
        return theExpenseDAO.findExpenseFromParticularCategory(userId,category);
    }

    @Override
    public List<Expense> getParticularCategoryAnnualExpense(User userId,String category,int year){
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theExpenseDAO.findByCategoryByDateAndTime(userId,category,startDate,endDate);
    }

    // Annual Expense statement from particular category  - total
    @Override
    public BigDecimal getParticularCategoryAnnualExpenseTotal(User userId,String category,int year){
        List<Expense> list=getParticularCategoryAnnualExpense(userId, category, year);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;
    }

    // Montly Expense statement from particular category
    @Override
    public List<Expense> getParticularCategoryMontlyExpense(User userId,String category,int year,int month){
        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        int dayOfMonth = lastDayOfMonth.getDayOfMonth();
        LocalDateTime startDate=LocalDateTime.of(year, month, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, month, dayOfMonth,23,59,10);
        return theExpenseDAO.findByCategoryByDateAndTime(userId,category,startDate,endDate);
    }

    // Montly Expense statement from particular category - total
    @Override
    public BigDecimal getParticularCategoryMontlyExpenseTotal(User userId,String category,int year,int month){
        List<Expense> list=getParticularCategoryMontlyExpense(userId,category,year,month);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;
    }


}
