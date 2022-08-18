package com.juggernauts.todoapp.components;

import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

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

    /**
     * Method under test: {@link RoutineTaskScheduler#updateRoutineTasks()}
     */
    @Test
    void testUpdateRoutineTasks3() {
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

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");

        Category category1 = new Category();
        category1.setCategoryId(123);
        category1.setCategoryName("running scheduleReminderNotifier cron job");
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
        task1.setCompleteBy(LocalDate.ofEpochDay(5L));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("running scheduleReminderNotifier cron job");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(5);
        task1.setTaskId(123);
        task1.setUser(user3);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskRepo.findAll()).thenReturn(taskList);
        routineTaskScheduler.updateRoutineTasks();
    }

    /**
     * Method under test: {@link RoutineTaskScheduler#updateRoutineTasks()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateRoutineTasks4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.components.RoutineTaskScheduler.lambda$updateRoutineTasks$0(RoutineTaskScheduler.java:43)
        //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:174)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1384)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
        //       at com.juggernauts.todoapp.components.RoutineTaskScheduler.updateRoutineTasks(RoutineTaskScheduler.java:43)
        //   In order to prevent updateRoutineTasks()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateRoutineTasks().
        //   See https://diff.blue/R013 to resolve this issue.

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
        task.setCompleteBy(null);
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

