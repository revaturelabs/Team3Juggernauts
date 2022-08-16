package com.juggernauts.todoapp.repos;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
=======
import com.juggernauts.todoapp.models.Reminder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepo extends JpaRepository<Reminder, Integer> {
<<<<<<< HEAD
=======
    @Query("SELECT r FROM Reminder r WHERE r.remindBy <= current_date")
    List<Reminder> getOutdatedReminders();
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
}
