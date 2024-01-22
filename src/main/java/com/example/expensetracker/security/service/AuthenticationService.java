package com.example.expensetracker.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.expensetracker.repository.UserEntityRepository;
import com.example.expensetracker.security.model.LoginRequest;
import com.example.expensetracker.security.model.UserEntity;

// import com.application.socialMedia.model.FollowRequest;
// import com.application.socialMedia.model.Page;
// import com.application.socialMedia.model.Role;
// import com.application.socialMedia.model.User;
// import com.application.socialMedia.repository.FollowRequestRepository;
// import com.application.socialMedia.repository.PageRepository;
// import com.application.socialMedia.repository.UserRepository;
// import com.application.socialMedia.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserEntityRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    
    private final AuthenticationManager authenticationManager;

    public String register(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        userRepo.save(user);
        String jwt = jwtService.generateToken(user);
        return jwt;
    }


    public String authenticate(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserEntity user = userRepo.findByUserName(request.getUserName()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return jwt;
    }

}
