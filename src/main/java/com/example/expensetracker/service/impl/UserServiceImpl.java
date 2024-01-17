package com.example.expensetracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository theUserRepository;

    @Override
    public List<User> getAllUsers(){
        return theUserRepository.findAll();
    }

    @Override
    public void insertNewUser(User newUser) {
        theUserRepository.save(newUser);
    }
}
