package com.example.expensetracker.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.expensetracker.model.Income;

public interface IncomeService {
    List<Income> getAllIncomes();
    List<Income> getIncomeByUserName(String userName);
    Income insertnewIncome(Income newIncome);
    Income insertnewIncomeFromAccount(Income newIncome);
    BigDecimal getTotalIncomeByUserName(String userName);
    List<Income> getAllAnualIncome(String userName,int year);
    BigDecimal getAllAnualIncomeTotal(String userName, int year);
    List<Income> getParticularUsersParticularSourceAnnualIncome(String userName,String source);
    List<Income> getParticularSourceAnnualIncome(String userName,String source,int year);
    BigDecimal getParticularSourceAnnualIncomeTotal(String userName,String source,int year);
    List<Income> getParticularSourceMontlyIncome(String userName,String source,int year,int month);
    BigDecimal getParticularSourceMontlyIncomeTotal(String userName,String source,int year,int month);
}
