package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public void setTaskRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public String addUser(User user) {
        userRepo.save(user);
        return user.toString();
    }

    @Transactional
    public int deleteUser(int id) {
        boolean userExists = userRepo.existsById(id);
        if (userExists) {
            userRepo.deleteById(id);
            return 1;
        }
        return -1;
    }

    @Transactional
    public int updateUser(int id, String email){
        User user = userRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("User with this id not found"));

        if(email != null && email.length() > 0 && !email.equals(user.getEmail())){
            user.setEmail(email);
            return 1;
        }
        return -1;
    }

    public int viewUser(int id) {
        boolean userExists = userRepo.existsById(id);
        if (!userExists) {
            return -1;
        }
        userRepo.findById(id);
        return 1;
    }

    public List<User> viewAllUsers(int id) {
        List<User> listOfUsers = userRepo.findAll();
        if(listOfUsers.isEmpty()){
            return null;
        }
        return listOfUsers;
    }
}
