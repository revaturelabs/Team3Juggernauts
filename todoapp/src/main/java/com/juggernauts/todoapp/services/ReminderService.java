package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.repos.ReminderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReminderService {
    private ReminderRepo reminderRepo;

    @Autowired
    public void setReminderRepo(ReminderRepo reminderRepo) {
        this.reminderRepo = reminderRepo;
    }

    @Transactional
    public int addReminder(Reminder reminder) {
        if (reminder.getReminderId() > 0){
            reminderRepo.save(reminder);
            return 1;
     }
     return-1;
    }

    @Transactional
    public int updateReminderDate(int id, Date date) {
        if (!reminderRepo.existsById(id)){
            return -1;
        }
        //not really sure how the whole Date class works, but the date remindBy will update here
        return 1;
    }

    @Transactional
    public int deleteReminder(int id) {
        boolean remExists = reminderRepo.existsById(id);
        if (remExists) {
            reminderRepo.deleteById(id);
            return 1;
        }
        return -1;
        }

    public int viewReminder(int id){
        boolean remExists = reminderRepo.existsById(id);
        if(remExists){
            reminderRepo.findById(id);
            return 1;
        }
        return -1;
    }

    public List<Reminder> getAllRemindersByTaskId(List<Integer> ids) {
        List<Reminder> remindersById = (List<Reminder>) reminderRepo.findAllById(ids);
        if (!remindersById.isEmpty()) {
            return remindersById;
        }
        return null;
    }

    public List<Reminder> getAllReminders(List<Integer> ids) {
       List<Reminder> remList = reminderRepo.findAll();
       if(!remList.isEmpty()){
           return remList;
       }
       return null;
    }

}
