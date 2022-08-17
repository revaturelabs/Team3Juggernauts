package com.juggernauts.todoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TaskService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class TaskServiceTest {
    @MockBean
    private TaskRepo taskRepo;

    @Autowired
    private TaskService taskService;

    /**
     * Method under test: {@link TaskService#addTask(Task)}
     */
    @Test
    void testAddTask() throws RuntimeException {
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
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setCompleteBy(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("Name");
        task.setReminders(new ArrayList<>());
        task.setTaskId(123);
        task.setUser(user1);
        when(taskRepo.save((Task) any())).thenReturn(task);

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
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task1.setCompleteBy(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("Name");
        task1.setReminders(new ArrayList<>());
        task1.setTaskId(123);
        task1.setUser(user3);
        taskService.addTask(task1);
        verify(taskRepo).save((Task) any());
        assertSame(category1, task1.getCategory());
        assertTrue(task1.isDone());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(task1.getCompleteBy()));
        assertEquals("Name", task1.getName());
        assertTrue(task1.getReminders().isEmpty());
        assertEquals("The characteristics of someone or something", task1.getDescription());
        assertSame(user3, task1.getUser());
        assertEquals(123, task1.getTaskId());
    }
}

