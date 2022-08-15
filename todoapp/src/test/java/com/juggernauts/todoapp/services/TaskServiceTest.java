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
class TaskServiceTest {

    private TaskService taskServiceMock;

    private TaskRepo taskRepoMock;

    private Task taskMock;

    private TaskController taskController;

    private Date completeBy = new Date();


    @Before
    public void setUp() {
        taskRepoMock = mock(TaskRepo.class);
        taskServiceMock = new TaskService(taskRepoMock);

        taskMock = new Task(1,1,"TaskName", false,
                1, 1, completeBy, "description" );
 }



    @Test
    void addTask() {
    }

    @Test
    void updateIsDone() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void getTask() {
    }

    @Test
    void getAllTasksByUserId() {
    }

    @Test
    void getAllTasksUnCompleteByUserId() {
    }

    @Test
    void getAllTasksCompleteByUserId() {
    }

    @Test
    void getAllTasksByGroup() {
    }

    @Test
    void getAllTasksByCategory() {
    }
}