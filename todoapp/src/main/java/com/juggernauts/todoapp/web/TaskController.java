package com.juggernauts.todoapp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;
import com.juggernauts.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired TaskRepo taskRepo;
    TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewTask(@RequestBody Task task, HttpServletRequest request) {

        // Will be removed after currentUser is implemented
        User currentUser = (User) request.getSession().getAttribute("USER");

        try {
            // This will all STAY once currentUser is available from userSession
            task.setUser(currentUser);
            taskService.addTask(task);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occured.");
        }

        return ResponseEntity.ok("Task added successfully");
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewAllTasks(@RequestBody HttpServletRequest request)  {
        // Testing
        User currentUser = (User) request.getSession().getAttribute("USER");

        try {
            List<Task> usersTasks;
            usersTasks = taskService.getTasks(currentUser);
            ObjectMapper om = new ObjectMapper();
            return ResponseEntity.ok(om.writeValueAsString(usersTasks));
        }
        catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("An error occured.");
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.unprocessableEntity().body("No tasks found.");
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("viewcategory")
    public ResponseEntity viewAllTasksByCategory(HttpServletRequest request) {
        // Testing
        User currentUser = (User) request.getSession().getAttribute("USER");

        HashMap<String, List<Task>> tasksPerCategory = taskService.getAllTasksPerCategory(currentUser);
        return ResponseEntity.ok(tasksPerCategory);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("resolve")
    public ResponseEntity completeTask(@RequestBody Task task) {
        System.out.println(task.getTaskId());
        int taskId = 0;
        try {
            taskId = task.getTaskId();
            System.out.println("TaskID: ");
            taskService.resolveTask(taskId);
            return ResponseEntity.ok(taskId);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(task.getTaskId());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("category")
    public ResponseEntity changeCategory(@RequestBody Task task, @RequestParam String categoryName) {
        System.out.println(task.getTaskId());
        try {
            System.out.println("TaskID: ");
            Task returnedTask = taskService.changeCategory(task.getTaskId(), categoryName);

            return ResponseEntity.ok(returnedTask);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(task.getTaskId());
        }
    }




}
