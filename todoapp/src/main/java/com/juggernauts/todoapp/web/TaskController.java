package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.ReminderService;
import com.juggernauts.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

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
    public ResponseEntity viewAllTasks() {
        // Testing
        User currentUser = new User(1, "testpass1", "test1");


        List<Task> usersTasks;
        usersTasks = taskService.getTasks(currentUser);
        usersTasks.stream().forEach(System.out::println);
        return ResponseEntity.ok(usersTasks);
    }
}
