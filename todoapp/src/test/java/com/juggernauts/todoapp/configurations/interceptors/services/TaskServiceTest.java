package com.juggernauts.todoapp.configurations.interceptors.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

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
        task.setCompleteBy(LocalDate.ofEpochDay(1L));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("Name");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
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
        task1.setCompleteBy(LocalDate.ofEpochDay(1L));
        task1.setDescription("The characteristics of someone or something");
        task1.setDone(true);
        task1.setName("Name");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);
        taskService.addTask(task1);
        verify(taskRepo).save((Task) any());
        assertSame(category1, task1.getCategory());
        assertTrue(task1.isDone());
        assertEquals("1970-01-02", task1.getCompleteBy().toString());
        assertEquals("Name", task1.getName());
        assertSame(user3, task1.getUser());
        assertTrue(task1.getReminders().isEmpty());
        assertEquals("The characteristics of someone or something", task1.getDescription());
        assertEquals(1, task1.getRepeatEvery());
        assertEquals(123, task1.getTaskId());
    }

    /**
     * Method under test: {@link TaskService#addTask(Task)}
     */
    @Test
    void testAddTask2() throws RuntimeException {
        when(taskRepo.save((Task) any())).thenThrow(new NoSuchElementException("foo"));

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
        assertThrows(NoSuchElementException.class, () -> taskService.addTask(task));
        verify(taskRepo).save((Task) any());
    }

    /**
     * Method under test: {@link TaskService#getTasks(User)}
     */
    @Test
    void testGetTasks() throws NoSuchElementException {
        when(taskRepo.findAll()).thenReturn(new ArrayList<>());

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        assertThrows(NoSuchElementException.class, () -> taskService.getTasks(user));
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getTasks(User)}
     */
    @Test
    void testGetTasks2() throws NoSuchElementException {
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

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");
        assertEquals(1, taskService.getTasks(user2).size());
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getTasks(User)}
     */
    @Test
    void testGetTasks3() throws NoSuchElementException {
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

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskRepo.findAll()).thenReturn(taskList);

        User user4 = new User();
        user4.setCategories(new ArrayList<>());
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerified(true);
        user4.setId(1);
        user4.setPassword("iloveyou");
        assertEquals(2, taskService.getTasks(user4).size());
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getTasks(User)}
     */
    @Test
    void testGetTasks4() throws NoSuchElementException {
        when(taskRepo.findAll()).thenThrow(new NoSuchElementException("foo"));

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        assertThrows(NoSuchElementException.class, () -> taskService.getTasks(user));
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getTasks(User)}
     */
    @Test
    void testGetTasks5() throws NoSuchElementException {
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

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(123);
        user2.setPassword("iloveyou");
        assertThrows(NoSuchElementException.class, () -> taskService.getTasks(user2));
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getAllTasksPerCategory(User)}
     */
    @Test
    void testGetAllTasksPerCategory() {
        when(taskRepo.findAll()).thenReturn(new ArrayList<>());

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        assertThrows(NoSuchElementException.class, () -> taskService.getAllTasksPerCategory(user));
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getAllTasksPerCategory(User)}
     */
    @Test
    void testGetAllTasksPerCategory2() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
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
        task.setName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepo.findAll()).thenReturn(taskList);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(1);
        user2.setPassword("iloveyou");
        assertEquals(2, taskService.getAllTasksPerCategory(user2).size());
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getAllTasksPerCategory(User)}
     */
    @Test
    void testGetAllTasksPerCategory3() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
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
        task.setName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
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
        category1.setCategoryName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
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
        task1.setName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
        task1.setReminders(new ArrayList<>());
        task1.setRepeatEvery(1);
        task1.setTaskId(123);
        task1.setUser(user3);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskRepo.findAll()).thenReturn(taskList);

        User user4 = new User();
        user4.setCategories(new ArrayList<>());
        user4.setEmail("jane.doe@example.org");
        user4.setEmailVerified(true);
        user4.setId(1);
        user4.setPassword("iloveyou");
        assertEquals(2, taskService.getAllTasksPerCategory(user4).size());
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getAllTasksPerCategory(User)}
     */
    @Test
    void testGetAllTasksPerCategory4() {
        when(taskRepo.findAll())
                .thenThrow(new NoSuchElementException("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!"));

        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");
        assertThrows(NoSuchElementException.class, () -> taskService.getAllTasksPerCategory(user));
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#getAllTasksPerCategory(User)}
     */
    @Test
    void testGetAllTasksPerCategory5() {
        User user = new User();
        user.setCategories(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setEmailVerified(true);
        user.setId(1);
        user.setPassword("iloveyou");

        Category category = new Category();
        category.setCategoryId(123);
        category.setCategoryName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
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
        task.setName("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepo.findAll()).thenReturn(taskList);

        User user2 = new User();
        user2.setCategories(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setEmailVerified(true);
        user2.setId(123);
        user2.setPassword("iloveyou");
        assertThrows(NoSuchElementException.class, () -> taskService.getAllTasksPerCategory(user2));
        verify(taskRepo).findAll();
    }

    /**
     * Method under test: {@link TaskService#resolveTask(int)}
     */
    @Test
    void testResolveTask() throws NoSuchElementException {
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
        Optional<Task> ofResult = Optional.of(task);

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
        when(taskRepo.save((Task) any())).thenReturn(task1);
        when(taskRepo.findById((Integer) any())).thenReturn(ofResult);
        taskService.resolveTask(123);
        verify(taskRepo).save((Task) any());
        verify(taskRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#resolveTask(int)}
     */
    @Test
    void testResolveTask2() throws NoSuchElementException {
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
        Optional<Task> ofResult = Optional.of(task);
        when(taskRepo.save((Task) any())).thenThrow(new NoSuchElementException("foo"));
        when(taskRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(NoSuchElementException.class, () -> taskService.resolveTask(123));
        verify(taskRepo).save((Task) any());
        verify(taskRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#resolveTask(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testResolveTask3() throws NoSuchElementException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.Optional.orElseThrow(Optional.java:290)
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.resolveTask(TaskService.java:95)
        //   In order to prevent resolveTask(int)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   resolveTask(int).
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
        task.setCompleteBy(LocalDate.ofEpochDay(1L));
        task.setDescription("The characteristics of someone or something");
        task.setDone(true);
        task.setName("Name");
        task.setReminders(new ArrayList<>());
        task.setRepeatEvery(1);
        task.setTaskId(123);
        task.setUser(user1);
        when(taskRepo.save((Task) any())).thenReturn(task);
        when(taskRepo.findById((Integer) any())).thenReturn(Optional.empty());
        taskService.resolveTask(123);
    }

    /**
     * Method under test: {@link TaskService#changeCategory(int, String)}
     */
    @Test
    void testChangeCategory() throws NoSuchElementException {
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
        Optional<Task> ofResult = Optional.of(task);
        when(taskRepo.findById((Integer) any())).thenReturn(ofResult);
        taskService.changeCategory(123, "Category Name");
        verify(taskRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#changeCategory(int, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChangeCategory2() throws NoSuchElementException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.Optional.orElseThrow(Optional.java:290)
        //       at com.juggernauts.todoapp.configurations.interceptors.services.TaskService.changeCategory(TaskService.java:103)
        //   In order to prevent changeCategory(int, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   changeCategory(int, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(taskRepo.findById((Integer) any())).thenReturn(Optional.empty());
        taskService.changeCategory(123, "Category Name");
    }

    /**
     * Method under test: {@link TaskService#changeCategory(int, String)}
     */
    @Test
    void testChangeCategory3() throws NoSuchElementException {
        when(taskRepo.findById((Integer) any())).thenThrow(new NoSuchElementException("foo"));
        assertThrows(NoSuchElementException.class, () -> taskService.changeCategory(123, "Category Name"));
        verify(taskRepo).findById((Integer) any());
    }
}

