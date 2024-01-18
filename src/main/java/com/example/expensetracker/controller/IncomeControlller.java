package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.User;
import com.example.expensetracker.service.IncomeService;
import org.springframework.web.bind.annotation.RequestParam;


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

    // yearly income from all sources
    @GetMapping("/listannualincome/{userId}/{year}")
    public List<Income> getAnualIncomeController(@PathVariable User userId,@PathVariable int year){
        return theIncomeService.getAllAnualIncome(userId,year);
    }

    // all income from particular source
    @GetMapping("/listuserssourceincome/{userId}/{source}")
    public List<Income> getMethodName(@PathVariable User userId, @PathVariable String source) {
        return theIncomeService.getParticularUsersParticularSourceAnnualIncome(userId,source);
    }
    
    // Annual Income statement from particular source
    @GetMapping("/listannualstatement/{source}/{year}")
    public List<Income> getAnnualIncomeStatementController(@PathVariable String source,@PathVariable int year) {
        return theIncomeService.getParticularSourceAnnualIncome(source,year);
    }

    // Montly  Income statement from particular source
    @GetMapping("/listmonthlystatement/{source}/{year}/{month}")
    public List<Income> getMonthlyIncomeController(@PathVariable String source,@PathVariable int year,@PathVariable int month) {
        return theIncomeService.getParticularSourceMontlyIncome(source, year, month);
    }
    
}
