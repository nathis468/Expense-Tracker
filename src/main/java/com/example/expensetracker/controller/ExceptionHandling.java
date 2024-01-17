package com.example.expensetracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(value=RuntimeException.class)
    public ResponseEntity<?> runTimeException(){
        return new ResponseEntity<>("Run time Exception",HttpStatus.BAD_REQUEST);
    }
}
