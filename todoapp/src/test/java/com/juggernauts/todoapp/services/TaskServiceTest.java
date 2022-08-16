package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.repos.TaskRepo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.juggernauts.todoapp.web.TaskController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class) //I think?
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    public TaskService taskServiceMock;

    @Mock //or maybe @MockBean im not sure how they really differ
    public TaskRepo taskRepoMock;

    public Task taskMock;

    public Date completeBy = new Date();


    @Before
    public void setUp() {
        taskRepoMock = mock(TaskRepo.class);
        taskServiceMock = new TaskService(taskRepoMock);

        taskMock = new Task(1,1,"TaskName", false,
                1, 1, completeBy, "description" );
 }



    @Test
   public void addTaskSuccessful() {
        int expected = 1;
        when(taskRepoMock.save(taskMock)).thenReturn(taskMock);
        int actual = taskServiceMock.addTask(taskMock);
        assertEquals(expected, actual);

    }

    @Test
    public void addTaskFail() {
        int creationFailed = -1;
        when(taskServiceMock.addTask(taskMock)).thenReturn(1);
       int actual = taskServiceMock.addTask(taskMock)

    }

    @Test
    public void updateIsDone() {
        int expected = 1;
        when(taskServiceMock.updateIsDone(taskMock)).thenReturn(1);
        int actual = taskServiceMock.updateIsDone(taskMock);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTask() {
    }

    @Test
    public void getTask() {
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