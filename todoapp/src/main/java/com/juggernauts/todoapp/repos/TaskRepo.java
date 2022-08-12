package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.Task;

import java.util.List;

public class TaskRepo extends IRepo<Task> {
    @Override
    public int register(Task user) {
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
    public Task get(String username) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        return null;
    }
}
