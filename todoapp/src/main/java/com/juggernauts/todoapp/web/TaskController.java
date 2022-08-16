package com.juggernauts.todoapp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.ReminderService;
import com.juggernauts.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("task")
public class TaskController {


    TaskService taskService;
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewTask(@RequestBody Task task) {

        // Will be removed after currentUser is implemented
        User currentUser = new User(1, "testpass1", "test1");

        // This will all STAY once currentUser is available from userSession
        task.setUser(currentUser);
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    };
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/bad")
    public ResponseEntity createNewTask2(@RequestBody Task task) {

        // Will be removed after currentUser is implemented
        User currentUser = new User(2, "testpass1", "test1");

        // This will all STAY once currentUser is available from userSession
        task.setUser(currentUser);
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    };

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewAllTasks() throws JsonProcessingException {
        // Testing
        User currentUser = new User(1, "testpass1", "test1");


        List<Task> usersTasks;
        usersTasks = taskService.getTasks(currentUser);
//        usersTasks.stream().forEach(System.out::println);
        System.out.println("NO ERROR YET!!!!!!");
        ObjectMapper om = new ObjectMapper();
        return ResponseEntity.ok(om.writeValueAsString(usersTasks));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("task/category")
    public ResponseEntity viewAllTasksByCategory() {
        // Testing
        User currentUser = new User(1, "testpass1", "test1");

        HashMap<String, List<Task>> tasksPerCategory = taskService.getAllTasksPerCategory(currentUser);
        return ResponseEntity.ok(tasksPerCategory);
    }

}
