package com.juggernauts.todoapp.services;

//import com.juggernauts.todoapp.models.Group;
import com.juggernauts.todoapp.models.Reminder;
//import com.juggernauts.todoapp.repos.GroupRepo;
import com.juggernauts.todoapp.repos.ReminderRepo;
<<<<<<< HEAD
=======

import java.util.List;

>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {
    private ReminderRepo reminderRepo;

    @Autowired
    public void setReminderRepo(ReminderRepo reminderRepo) {this.reminderRepo = reminderRepo;}

    public String addReminder(Reminder reminder) {
        reminderRepo.save(reminder);
        return reminder.toString();
<<<<<<< HEAD
    };
=======
    }

    public List<Reminder> getAllOutdatedReminders() {
        return reminderRepo.getOutdatedReminders();
    }

    public void deleteReminders(List<Reminder> reminders) {
        reminderRepo.deleteAll(reminders);
    }
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
}
