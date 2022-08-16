package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repositories.UserRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String addUser(User user) {
        userRepo.save(user);
        return user.toString();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
}