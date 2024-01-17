package com.example.expensetracker.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.Income;
import com.example.expensetracker.repository.IncomeRepository;
import com.example.expensetracker.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    private IncomeRepository theIncomeRepository;

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
}
