package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.model.User;

public interface UserService {
    List<User> getAllUsers();
    void insertNewUser(User newUser);
}
