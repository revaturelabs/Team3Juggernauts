package com.juggernauts.todoapp.components;

import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
class RoutineTaskSchedulerTest {
    @Autowired
    private RoutineTaskScheduler routineTaskScheduler;

    @MockBean
    private TaskRepo taskRepo;

    /**
     * Method under test: {@link RoutineTaskScheduler#updateRoutineTasks()}
     */
    @Test
    void testUpdateRoutineTasks() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     RoutineTaskScheduler.taskRepo
        //     RoutineTaskScheduler.taskService

        when(taskRepo.findAll()).thenReturn(new ArrayList<>());
        routineTaskScheduler.updateRoutineTasks();
    }

    /**
     * Method under test: {@link RoutineTaskScheduler#updateRoutineTasks()}
     */
    @Test
    void testUpdateRoutineTasks2() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     RoutineTaskScheduler.taskRepo
        //     RoutineTaskScheduler.taskService

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

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepo.findAll()).thenReturn(taskList);
        routineTaskScheduler.updateRoutineTasks();
    }
}

