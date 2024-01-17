package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Income;
import com.example.expensetracker.service.IncomeService;

@RestController
@RequestMapping("/income")
public class IncomeControlller {
    @Autowired
    private IncomeService theIncomeService;

    @GetMapping("/listall")
    public List<Income> getAllIncomesController(){
        return theIncomeService.getAllIncomes();
    }

    @PostMapping("/insertincome")
    public Income insertnewIncomeController(@RequestBody Income newIncome){
        theIncomeService.insertnewIncome(newIncome);
        return newIncome;
    }

    @GetMapping("/listtotalincome")
    public String getTotalIncomeController(){
        BigDecimal res=theIncomeService.getTotalIncome();
        return "Total Income : "+res;
    }
}
