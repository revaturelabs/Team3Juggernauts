package com.juggernauts.todoapp.services;

//import com.juggernauts.todoapp.models.Group;
import com.juggernauts.todoapp.models.Reminder;
//import com.juggernauts.todoapp.repos.GroupRepo;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.repos.ReminderRepo;

import java.util.List;

import com.juggernauts.todoapp.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {
    @Autowired
    TaskRepo taskRepo;
    private ReminderRepo reminderRepo;

    @Autowired
    public void setReminderRepo(ReminderRepo reminderRepo) {this.reminderRepo = reminderRepo;}

    public String addReminder(Reminder reminder, int taskId) {
        Task task = taskRepo.getReferenceById(taskId);
        reminder.setTask(task);
        reminderRepo.save(reminder);
        return reminder.toString();
    }

    public List<Reminder> getAllOutdatedReminders() {
        return reminderRepo.getOutdatedReminders();
    }

    public void deleteReminders(List<Reminder> reminders) {
        reminderRepo.deleteAll(reminders);
    }
}
