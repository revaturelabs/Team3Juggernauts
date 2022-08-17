package com.juggernauts.todoapp.serviceTests;

import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;
import com.juggernauts.todoapp.services.TaskService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) //I think?
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    public TaskService taskServiceMock;

    @Mock //or maybe @MockBean im not sure how they really differ
    public TaskRepo taskRepoMock;


    public Task taskMock;


    public User userMock;

    public Date completeBy = new Date();


    @Before
    public void setUp() {
        taskRepoMock = mock(TaskRepo.class);
        taskServiceMock = new TaskService(taskRepoMock);

        userMock = new User(1, "email", "pass");
        taskMock = new Task("taskOne", "descriptionOne", userMock );
    }



    @Test
    public void addTaskSuccessful() {
        String expected = taskMock.toString();
        when(taskRepoMock.save(taskMock)).thenReturn(taskMock);
        String actual = taskServiceMock.addTask(taskMock);
        assertEquals(expected, actual);

    }

    @Test
    public void addTaskFail() {
        Task task = null;
       String expected = task.toString();
        when(taskRepoMock.save(taskMock)).thenReturn(taskMock);
       String actual = taskServiceMock.addTask(taskMock);
        assertEquals(expected, actual);

    }
    @Test
    public void deleteTask() {
        int expected =1;
        int actual = taskServiceMock.deleteTask(taskMock.getTaskId());
        assertEquals(expected, actual);
    }

    @Test
    public void updateIsDone() {
        int expected = 1;
        when(taskServiceMock.updateIsDone(taskMock)).thenReturn(1);
        int actual = taskServiceMock.updateIsDone(taskMock);
        assertEquals(expected, actual);
    }

    @Test
    public void successfulGetTask() {
        int expected = 1;
//        when(taskRepoMock.findById(taskMock.getTaskId())).thenReturn(Optional.ofNullable(taskMock));
//        when(taskServiceMock.getTask(taskMock.getTaskId())).thenReturn(1);
//        int actual = taskServiceMock.getTask(1);
//        assertEquals(expected,actual);
    }

    @Test
    public void getAllTasksByUserId() {
    }

    @Test
    public void getAllTasksUnCompleteByUserId() {
    }

    @Test
    public void getAllTasksCompleteByUserId() {
    }

    @Test
    public void getAllTasksByGroup() {
    }

    @Test
    public void getAllTasksByCategory() {
    }
}