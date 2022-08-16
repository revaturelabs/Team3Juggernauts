package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createNewTask(@RequestBody Task task, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER");
        task.setUser(currentUser);
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/bad")
    public ResponseEntity<Task> createNewTask2(@RequestBody Task task, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER");
        task.setUser(currentUser);
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> viewAllTasks(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER");
        List<Task> usersTasks;
        usersTasks = taskService.getTasks(currentUser);
        usersTasks.stream().forEach(System.out::println);
        return ResponseEntity.ok(usersTasks);
    }
}
