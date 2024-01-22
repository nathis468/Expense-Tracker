package com.example.expensetracker.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Income>> getAllIncomesController(){
        return new ResponseEntity<>(theIncomeService.getAllIncomes(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listall/{userName}")
    public ResponseEntity<List<Income>> getAllIncomesByUserNameController(@PathVariable("userName") String userName){
        return new ResponseEntity<>(theIncomeService.getIncomeByUserName(userName),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/insertincome")
    public ResponseEntity<Income> insertnewIncomeController(@RequestBody Income newIncome){
        return new ResponseEntity<>(theIncomeService.insertnewIncome(newIncome),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listtotalincome/{userName}")
    public ResponseEntity<String> getTotalIncomeController(@PathVariable("userName") String userName){
        BigDecimal res=theIncomeService.getTotalIncomeByUserName(userName);
        return new ResponseEntity<>("Total Income : "+res,HttpStatus.OK);
    }

    // yearly income from all sources
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualincome/{userName}/{year}")
    public ResponseEntity<List<Income>> getAnualIncomeController(@PathVariable String userName,@PathVariable int year){
        return new ResponseEntity<>(theIncomeService.getAllAnualIncome(userName,year),HttpStatus.OK);
    }

    // yearly income from all source - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualincometotal/{userName}/{year}")
    public ResponseEntity<String> getMethodName(@PathVariable String userName,@PathVariable int year) {
        BigDecimal res=theIncomeService.getAllAnualIncomeTotal(userName, year);
        return new ResponseEntity<>("Total Income : "+res,HttpStatus.OK);
    }

    // all income from particular source
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listuserssourceincome/{userName}/{source}")
    public ResponseEntity<List<Income>> getMethodName(@PathVariable String userName, @PathVariable String source) {
        return new ResponseEntity<>(theIncomeService.getParticularUsersParticularSourceAnnualIncome(userName,source),HttpStatus.OK);
    }
    
    // Annual Income statement from particular source   
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listannualstatement/{userName}/{source}/{year}")
    public ResponseEntity<List<Income>> getAnnualIncomeStatementController(@PathVariable String userName,@PathVariable String source,@PathVariable int year) {
        return new ResponseEntity<>(theIncomeService.getParticularSourceAnnualIncome(userName,source,year),HttpStatus.OK);
    }

    // Annual Income statement from particular source  - total
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listannualstatementtotal/{userName}/{source}/{year}")
    public ResponseEntity<String> getAnnualIncomeStatementTotalController(@PathVariable String userName,@PathVariable String source,@PathVariable int year) {
        BigDecimal totalIncome = theIncomeService.getParticularSourceAnnualIncomeTotal(userName,source,year);
        return new ResponseEntity<>("Total Annual Income from "+source+" : "+totalIncome,HttpStatus.OK);
    }

    // Montly Income statement from particular source
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/listmonthlystatement/{userName}/{source}/{year}/{month}")
    public ResponseEntity<List<Income>> getMonthlyIncomeController(@PathVariable String userName,@PathVariable String source,@PathVariable int year,@PathVariable int month) {
        return new ResponseEntity<>(theIncomeService.getParticularSourceMontlyIncome(userName,source, year, month),HttpStatus.OK);
    }    

    // Montly Income statement from particular source
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('FINANCIAL_ADVISOR')")
    @GetMapping("/listmonthlystatementtotal/{userName}/{source}/{year}/{month}")
    public ResponseEntity<String> getMonthlyIncomeTotalController(@PathVariable String userName,@PathVariable String source,@PathVariable int year,@PathVariable int month) {
        BigDecimal totalIncome = theIncomeService.getParticularSourceMontlyIncomeTotal(userName,source, year, month);
        return new ResponseEntity<>("Total Montly("+month+") Income from "+source+" : "+totalIncome,HttpStatus.OK);
    } 
}
