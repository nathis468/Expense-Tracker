package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listall")
    public List<Income> getAllIncomesController(){
        return theIncomeService.getAllIncomes();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listall/{userName}")
    public List<Income> getAllIncomesByUserNameController(@PathVariable("userName") String userName){
        return theIncomeService.getIncomeByUserName(userName);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/insertincome")
    public Income insertnewIncomeController(@RequestBody Income newIncome){
        return theIncomeService.insertnewIncome(newIncome);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listtotalincome/{userName}")
    public String getTotalIncomeController(@PathVariable("userName") String userName){
        BigDecimal res=theIncomeService.getTotalIncomeByUserName(userName);
        return "Total Income : "+res;
    }

    // yearly income from all sources
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualincome/{userName}/{year}")
    public List<Income> getAnualIncomeController(@PathVariable String userName,@PathVariable int year){
        return theIncomeService.getAllAnualIncome(userName,year);
    }

    // yearly income from all source - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualincometotal/{userName}/{year}")
    public String getMethodName(@PathVariable String userName,@PathVariable int year) {
        BigDecimal res=theIncomeService.getAllAnualIncomeTotal(userName, year);
        return "Total Income : "+res;
    }

    // all income from particular source
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listuserssourceincome/{userName}/{source}")
    public List<Income> getMethodName(@PathVariable String userName, @PathVariable String source) {
        return theIncomeService.getParticularUsersParticularSourceAnnualIncome(userName,source);
    }
    
    // Annual Income statement from particular source   
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualstatement/{userName}/{source}/{year}")
    public List<Income> getAnnualIncomeStatementController(@PathVariable String userName,@PathVariable String source,@PathVariable int year) {
        return theIncomeService.getParticularSourceAnnualIncome(userName,source,year);
    }

    // Annual Income statement from particular source  - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualstatementtotal/{userName}/{source}/{year}")
    public String getAnnualIncomeStatementTotalController(@PathVariable String userName,@PathVariable String source,@PathVariable int year) {
        BigDecimal totalIncome = theIncomeService.getParticularSourceAnnualIncomeTotal(userName,source,year);
        return "Total Annual Income from "+source+" : "+totalIncome;
    }

    // Montly Income statement from particular source
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listmonthlystatement/{userName}/{source}/{year}/{month}")
    public List<Income> getMonthlyIncomeController(@PathVariable String userName,@PathVariable String source,@PathVariable int year,@PathVariable int month) {
        return theIncomeService.getParticularSourceMontlyIncome(userName,source, year, month);
    }    

    // Montly Income statement from particular source
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listmonthlystatementtotal/{userName}/{source}/{year}/{month}")
    public String getMonthlyIncomeTotalController(@PathVariable String userName,@PathVariable String source,@PathVariable int year,@PathVariable int month) {
        BigDecimal totalIncome = theIncomeService.getParticularSourceMontlyIncomeTotal(userName,source, year, month);
        return "Total Montly("+month+") Income from "+source+" : "+totalIncome;
    } 
}
