package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.dao.IncomeDAO;
import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.IncomeRepository;
import com.example.expensetracker.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    private IncomeRepository theIncomeRepository;

    @Autowired
    private IncomeDAO theIncomeDAO;

    @Override
    public List<Income> getAllIncomes() {
        return theIncomeRepository.findAll();
    }

    @Override
    public void insertnewIncome(Income newIncome) {
        theIncomeRepository.save(newIncome);
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

    @Override
    public List<Income> getParticularUsersParticularSourceAnnualIncome(User userId,String source){
        return theIncomeDAO.findIncomeFromParticularSource(userId,source);
    }

    @Override
    public List<Income> getParticularSourceAnnualIncome(String source,int year){
        LocalDateTime startDate=LocalDateTime.of(year, 1, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, 12, 31,23,59,10);
        return theIncomeDAO.findBySourceByDateAndTime(source,startDate,endDate);
    }

    @Override
    public List<Income> getParticularSourceMontlyIncome(String source,int year,int month){
        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        int dayOfMonth = lastDayOfMonth.getDayOfMonth();
        LocalDateTime startDate=LocalDateTime.of(year, month, 1,0,0,10);
        LocalDateTime endDate=LocalDateTime.of(year, month, dayOfMonth,23,59,10);
        return theIncomeDAO.findBySourceByDateAndTime(source,startDate,endDate);
    }
}
