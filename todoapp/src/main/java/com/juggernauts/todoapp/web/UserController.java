package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    User testuser = new User(1, "testpassword", "testemail");

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    };

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity viewAllTasks(@RequestBody User user) {
        List<Task> usersTasks;
        usersTasks = taskService.getTasks(user);
        usersTasks.stream().forEach(System.out::println);
        return ResponseEntity.ok(usersTasks);
    }
}
