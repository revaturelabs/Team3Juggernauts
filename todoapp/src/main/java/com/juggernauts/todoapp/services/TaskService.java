package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.ReminderRepo;
import com.juggernauts.todoapp.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    @Autowired
    public void setTaskRepo(TaskRepo taskRepo) {this.taskRepo = taskRepo;}

    public String addTask(Task task) {
        taskRepo.save(task);
        return task.toString();
    };

    public List<Task> getTasks(User user) {
        List<Task> allTasks = taskRepo.findAll();
        System.out.println("ALL TASKS: "+allTasks);
        List<Task> usersTasks = allTasks.stream()
                .filter(task -> task.getUser().getId() == user.getId())
                .collect(Collectors.toList());
        return usersTasks;
    }
}
