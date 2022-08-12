package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.User;

import java.util.List;

public class UserRepo extends IRepo<User>{
    @Override
    public int register(User user) {
        return 0;
    }

    @Override
    public int login(String username, String password) {
        return 0;
    }

    @Override
    public String viewProfile(String username) {
        return null;
    }

    @Override
    public String updateProfile(String username, String password, String email) {
        return null;
    }

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
