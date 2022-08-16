package com.juggernauts.todoapp.web;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.services.CategoryService;
=======
import com.juggernauts.todoapp.models.EmailVerification;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import com.juggernauts.todoapp.services.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("emailVerification")
public class EmailVerificationController {

    @Autowired
    EmailVerificationService emailVerificationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
<<<<<<< HEAD
    public ResponseEntity createNewEmailVerification(@RequestBody EmailVerification emailVerification) {
=======
    public ResponseEntity<EmailVerification> createNewEmailVerification(@RequestBody EmailVerification emailVerification) {
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
        emailVerificationService.addEmailVerification(emailVerification);
        return ResponseEntity.ok(emailVerification);
    };
}
