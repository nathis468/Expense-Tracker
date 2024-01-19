package com.example.expensetracker.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.User;

public interface IncomeService {
    List<Income> getAllIncomes();
    void insertnewIncome(Income newIncome);
    BigDecimal getTotalIncome();
    List<Income> getAllAnualIncome(User userId,int year);
    BigDecimal getAllAnualIncomeTotal(User userId, int year);
    List<Income> getParticularUsersParticularSourceAnnualIncome(User userId,String source);
    List<Income> getParticularSourceAnnualIncome(User userId,String source,int year);
    BigDecimal getParticularSourceAnnualIncomeTotal(User userId,String source,int year);
    List<Income> getParticularSourceMontlyIncome(User userId,String source,int year,int month);
    BigDecimal getParticularSourceMontlyIncomeTotal(User userId,String source,int year,int month);
}
