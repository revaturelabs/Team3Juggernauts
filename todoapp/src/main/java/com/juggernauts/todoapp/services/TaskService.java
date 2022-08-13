package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.repos.ReminderRepo;
import com.juggernauts.todoapp.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    @Autowired
    public void setTaskRepo(TaskRepo taskRepo) {this.taskRepo = taskRepo;}

    public String addTask(Task task) {
        taskRepo.save(task);
        return task.toString();
    };
}
