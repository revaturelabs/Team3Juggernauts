package com.juggernauts.todoapp.web;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Task;
=======
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.TaskService;
import com.juggernauts.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {
    User testuser = new User(1, "testpassword", "testemail");
=======
@RestController
@RequestMapping("user")
public class UserController {
    User testuser = new User("password", "email@test.com", true);
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
    public ResponseEntity createNewUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    };


=======
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
}
