package com.juggernauts.todoapp.services;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;
import com.juggernauts.todoapp.repos.UserRepo;
=======
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.UserRepo;

import java.util.List;

>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
<<<<<<< HEAD
    private UserRepo userRepo;

    @Autowired
    public void setTaskRepo(UserRepo userRepo) {this.userRepo = userRepo;}
=======
    @Autowired
    private UserRepo userRepo;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563

    public String addUser(User user) {
        userRepo.save(user);
        return user.toString();
<<<<<<< HEAD
    };
=======
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
}
