package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dao.ExpenseDAO;
import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.Transaction;
import com.example.expensetracker.model.TransactionType;
import com.example.expensetracker.repository.AccountRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository theExpenseRepository;

    @Autowired
    private AccountRepository theAccountRepository;

    @Autowired
    private ExpenseDAO theExpenseDAO;

    @Override
    public List<Expense> getAllExpenses(){
        return theExpenseRepository.findAll();
    }

    @Override
    public List<Expense> getExpenseByUserName(String userName){
        return theExpenseRepository.findByUserName(userName);
    }

    @Override
    public Expense insertnewExpense(Expense newExpense) {
        String username = newExpense.getUserName();
        
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
            return expense;
        }
        return newExpense;
    }

    @Override
    public Expense insertnewExpenseFromAccount(Expense newExpense){
        return theExpenseRepository.save(newExpense);
    }

    @Override
    public BigDecimal getTotalExpenseByUserName(String userName) {
        List<Expense> list=theExpenseRepository.findAll();
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;    
    }

    @Override
    public List<Expense> getAllAnualExpense(String userName, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theExpenseDAO.findAnnualExpenseByUserName(userName,startDate,endDate);
    }

    // yearly expense from all category - total
    public BigDecimal getAllAnualExpenseTotal(String userName, int year) {
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        List<Expense> list=theExpenseDAO.findAnnualExpenseByUserName(userName,startDate,endDate);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;
    }

    @Override
    public List<Expense> getParticularUsersParticularCategoryAnnualExpense(String userName,String category){
        return theExpenseDAO.findExpenseFromParticularCategory(userName,category);
    }

    @Override
    public List<Expense> getParticularCategoryAnnualExpense(String userName,String category,int year){
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theExpenseDAO.findByCategoryByDateAndTime(userName,category,startDate,endDate);
    }

    // Annual Expense statement from particular category  - total
    @Override
    public BigDecimal getParticularCategoryAnnualExpenseTotal(String userName,String category,int year){
        List<Expense> list=getParticularCategoryAnnualExpense(userName, category, year);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;
    }

    // Montly Expense statement from particular category
    @Override
    public List<Expense> getParticularCategoryMontlyExpense(String userName,String category,int year,int month){
        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        int dayOfMonth = lastDayOfMonth.getDayOfMonth();
        LocalDateTime startDate=LocalDateTime.of(year, month, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, month, dayOfMonth,23,59,10);
        return theExpenseDAO.findByCategoryByDateAndTime(userName,category,startDate,endDate);
    }

    // Montly Expense statement from particular category - total
    @Override
    public BigDecimal getParticularCategoryMontlyExpenseTotal(String userName,String category,int year,int month){
        List<Expense> list=getParticularCategoryMontlyExpense(userName,category,year,month);
        BigDecimal totalExpense=BigDecimal.ZERO;
        for(Expense expense:list){
            totalExpense=totalExpense.add(expense.getAmount());
        }
        return totalExpense;
    }

    // 
    @Override
    public HashMap<String,BigDecimal> getIndividualExpense(String userName,String date){
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime starTime = LocalDate.parse(date, formatter1).atStartOfDay();
        System.out.println("startdate"+starTime);
        LocalDateTime endTime = LocalDate.parse(date, formatter2).atTime(23, 59, 59);

        System.out.println("enddate"+endTime);
        List<Expense> list1 = new ArrayList<Expense>(theExpenseDAO.findIndividualExpenseByUserName(userName,starTime,endTime));
        HashMap<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
        BigDecimal totalAmount = new BigDecimal(0);
        for (Expense expense : list1) {
            if(resultMap.containsKey(expense.getCategory()) == true){
                resultMap.put(expense.getCategory(),resultMap.get(expense.getCategory()+expense.getAmount()));
            }
            else{
                resultMap.put(expense.getCategory(), expense.getAmount());
            }
            totalAmount = totalAmount.add(expense.getAmount());
        }
        resultMap.put("Total Expense Spend : ",totalAmount);
        return resultMap;
    }
}
