package com.example.expensetracker.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.security.model.LoginRequest;
import com.example.expensetracker.security.model.UserEntity;
import com.example.expensetracker.security.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController { 
    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public String register(@RequestBody UserEntity request){
        return service.register(request);
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody LoginRequest request){  
        System.out.println(request);
        return service.authenticate(request);
    }
}
