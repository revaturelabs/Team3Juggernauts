package com.juggernauts.todoapp.components;

import com.juggernauts.todoapp.services.MailService;
import com.juggernauts.todoapp.services.ReminderService;
import com.juggernauts.todoapp.services.TaskService;
import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.repos.TaskRepo;
import com.juggernauts.todoapp.utils.DateFormater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The component for maintaining routine tasks
 * @author Josh
 */
@Component
public class RoutineTaskScheduler {
    private static final Logger logger = LogManager.getLogger(RoutineTaskScheduler.class);

    @Autowired private TaskService taskService;

    @Autowired private TaskRepo taskRepo;

    /**
     * Schedules a reminder notifier under the given task
     * The task will run every 10 seconds
     */
    @Scheduled(cron = "@Hourly")
    public void updateRoutineTasks() {
        List<Task> allTasks = taskRepo.findAll();
        List<Task> expiredTasks = allTasks.stream()
                .filter(task -> task.getCompleteBy().isBefore(LocalDate.now())).collect(Collectors.toList());
        if (expiredTasks.isEmpty()) return;

        logger.info("running scheduleReminderNotifier cron job");

        expiredTasks.forEach(task -> {
            task.setCompleteBy(DateFormater.addToDate(task.getCompleteBy(), task.getRepeatEvery()));
        });
    }
}


