package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.EmailVerification;
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
    public ResponseEntity<EmailVerification> createNewEmailVerification(@RequestBody EmailVerification emailVerification) {
        emailVerificationService.addEmailVerification(emailVerification);
        return ResponseEntity.ok(emailVerification);
    };
}
