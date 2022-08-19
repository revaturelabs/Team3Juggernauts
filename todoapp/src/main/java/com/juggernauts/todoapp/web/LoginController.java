package com.juggernauts.todoapp.web;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.EmailVerificationService;
import com.juggernauts.todoapp.services.MailService;
import com.juggernauts.todoapp.services.UserService;
import com.juggernauts.todoapp.utils.Tokens;

import feign.FeignException;
import feign.FeignException.Forbidden;

/**
 * Represents a RESTController handling mappings for the login endpoint
 * @author Jacob
 */
@RestController
public class LoginController {
    @Autowired private UserService userService;
    @Autowired private MailService mailService;
    @Autowired private EmailVerificationService emailVerificationService;

    /**
     * A POST mapping for the /login endpoint
     * <p>Attempts to log a user into the application by email and password</p>
     * @param email The user's email
     * @param password The user's password
     * @param request The request information
     * @return A string message showing whether the login was successful
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("USER") != null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You are already logged in.");

        // get user by the email
        User gotUser = userService.getUser(user.getEmail());

        // invalid password
        if (gotUser == null || !gotUser.getPassword().equals(user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login details.");

        if (!gotUser.isEmailVerified())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not verified.");

        session.setAttribute("USER", gotUser);
        return ResponseEntity.accepted().body("You have logged in.");
    }

    /**
     * A POST mapping for the /register endpoint
     * <p>Attempts to register a user to the application by email and password</p>
     * @param request The request information
     * @return A string message showing whether the registration was successful
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/register")
    public ResponseEntity<String> register(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        // if the session has a user attributed to it already, then they must be logged in
        if (session.getAttribute("USER") != null)
            return ResponseEntity.badRequest().body("You are already logged in.");

        // make sure theres no users with the input email
        if (userService.getUser(user.getEmail()) != null)
            return ResponseEntity.badRequest().body("This email is taken.");

        user = userService.addUser(user);

        // add email verification to the DB
        String token = Tokens.generateToken(user);

        // send user a welcome email with an email verification URL
        String verificationURL = Tokens.generateTokenURL(request, user, token);

        try {
            mailService.sendEmail(
                user.getEmail(), 
                "Welcome to habitu.al!", 
                String.format("You have successfully registered to habitu.al. Click this link to verify your email: %s", verificationURL)
            );
        } catch (Forbidden exc) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your e-mail must be registered with Mailgun.");
        }
    
        // registration is successful, add user to DB
        userService.addUser(user);
        EmailVerification ev = new EmailVerification(token, user);
        emailVerificationService.addEmailVerification(ev);

        return ResponseEntity.status(HttpStatus.CREATED).body("Welcome.");
    }
}
