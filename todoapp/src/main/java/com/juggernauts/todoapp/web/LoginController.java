package com.juggernauts.todoapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.UserService;

/**
 * Represents a RESTController handling mappings for the login endpoint
 * @author Jacob
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * A POST mapping for the /login endpoint
     * <p>Attempts to log a user into the application by email and password</p>
     * @param email The user's email
     * @param password The user's password
     * @param request The request information
     * @return A string message showing whether the login was successful
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("USER") != null)
            return new ResponseEntity<>("You are already logged in.", HttpStatus.NOT_ACCEPTABLE);
            
        // get user by the email
        User user = userService.findUserByEmail(email);

        // invalid password
        if (user == null || !user.getPassword().equals(password))
            return new ResponseEntity<>("Invalid login details.", HttpStatus.UNAUTHORIZED);

        session.setAttribute("USER", user);
        return new ResponseEntity<>("You have logged in.", HttpStatus.ACCEPTED);
    }

    /**
     * A POST mapping for the /register endpoint
     * <p>Attempts to register a user to the application by email and password</p>
     * @param email The user's email
     * @param password The user's password
     * @param request The request information
     * @return A string message showing whether the registration was successful
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        // if the session has a user attributed to it already, then they must be logged in
        if (session.getAttribute("USER") != null)
            return new ResponseEntity<>("You are already logged in.", HttpStatus.BAD_REQUEST);

        // make sure theres no users with the input email
        if (userService.findUserByEmail(email) != null)
            return new ResponseEntity<>("This email is taken.", HttpStatus.BAD_REQUEST);

        // registration is successful, add user to DB
        userService.addUser(new User(password, email, false));
        return new ResponseEntity<>("Welcome.", HttpStatus.CREATED);
    }
}
