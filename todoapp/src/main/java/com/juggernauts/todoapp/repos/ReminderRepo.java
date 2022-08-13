package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepo extends JpaRepository<Reminder, Integer> {
}
