package com.example.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.User;
import com.example.expensetracker.service.UserService;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class UserController {
    @Autowired
    private UserService theUserService;

    @GetMapping("/listall")
    public List<User> getAllUsersController(){
        return theUserService.getAllUsers();
    }

    @PostMapping("/insertuser")
    public User insertNewUserController(@RequestBody User newUser){
        theUserService.insertNewUser(newUser);
        return newUser;
    }
}
