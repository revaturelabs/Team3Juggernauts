package com.juggernauts.todoapp.configurations.interceptors.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.ReminderRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReminderService.class})
@
         ({"josh"})
@ExtendWith(SpringExtension.class)
class ReminderServiceTest {
    @MockBean
    private ReminderRepo reminderRepo;

    @Autowired
    private ReminderService reminderService;

    /**
     * Method under test: {@link ReminderService#addReminder(Reminder)}
     */
    @Test
    void testAddReminder() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("Category Name");
        category.setTasks(new ArrayList<>());
        category.setUser(user);

        User user1 = new User();
        user1.setCategories(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setEmailVerified(true);
        user1.setId(1);
        user1.setPassword("iloveyou");

        Task task = new Task();
        task.setCategory(category);
        task.setCompleteBy(LocalDate.ofEpochDay(1L));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("Name");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        Reminder reminder = new Reminder();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        reminder.setRemindBy(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        reminder.setReminderId(123);
        reminder.setRepeatEvery(1);
        reminder.setTask(task);
        when(reminderRepo.save((Reminder) any())).thenReturn(reminder);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("Category Name");
        category1.setTasks(new ArrayList<>());
        category1.setUser(user2);

        User user3 = new User();
        user3.setCategories(new ArrayList<>());
        user3.setEmail("jane.doe@example.org");
        user3.setEmailVerified(true);
        user3.setId(1);
        user3.setPassword("iloveyou");

        Task task1 = new Task();
        task1.setCategory(category1);
        task1.setCompleteBy(LocalDate.ofEpochDay(1L));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("Name");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);

        Reminder reminder1 = new Reminder();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        reminder1.setRemindBy(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        reminder1.setReminderId(123);
        reminder1.setRepeatEvery(1);
        reminder1.setTask(task1);
        reminderService.addReminder(reminder1);
        verify(reminderRepo).save((Reminder) any());
    }

    /**
     * Method under test: {@link ReminderService#getAllOutdatedReminders()}
     */
    @Test
    void testGetAllOutdatedReminders() {
        ArrayList<Reminder> reminderList = new ArrayList<>();
        when(reminderRepo.getOutdatedReminders()).thenReturn(reminderList);
        List<Reminder> actualAllOutdatedReminders = reminderService.getAllOutdatedReminders();
        assertSame(reminderList, actualAllOutdatedReminders);
        assertTrue(actualAllOutdatedReminders.isEmpty());
        verify(reminderRepo).getOutdatedReminders();
    }

    /**
     * Method under test: {@link ReminderService#deleteReminders(List)}
     */
    @Test
    void testDeleteReminders() {
        doNothing().when(reminderRepo).deleteAll((Iterable<Reminder>) any());
        reminderService.deleteReminders(new ArrayList<>());
        verify(reminderRepo).deleteAll((Iterable<Reminder>) any());
        assertTrue(reminderService.getAllOutdatedReminders().isEmpty());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link ReminderService}
     *   <li>{@link ReminderService#setReminderRepo(ReminderRepo)}
     * </ul>
     */
    @Test
    void testConstructor() {
        ReminderService actualReminderService = new ReminderService();
        actualReminderService.setReminderRepo(mock(ReminderRepo.class));
        assertTrue(actualReminderService.getAllOutdatedReminders().isEmpty());
    }
}

