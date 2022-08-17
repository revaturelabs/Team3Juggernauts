package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.User;
<<<<<<< HEAD
import com.juggernauts.todoapp.repositories.UserRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
=======
import com.juggernauts.todoapp.repos.UserRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
>>>>>>> f7791cd5abc766eb0f668e623c688b21a342ebcb
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

<<<<<<< HEAD
    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
}
=======
    public User getUser(String email) {
        return userRepo.findOne(email);
    }

    public User getUser(int id) {
        return userRepo.getReferenceById(id);
    }

    public Optional<User> getUser(User user) {
        return userRepo.findOne(Example.of(user));
    }

    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepo.save(user);
    }
}
>>>>>>> f7791cd5abc766eb0f668e623c688b21a342ebcb
