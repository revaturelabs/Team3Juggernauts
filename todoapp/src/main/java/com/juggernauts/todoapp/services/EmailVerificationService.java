package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.VerifyEmail;
import com.juggernauts.todoapp.repos.EmailVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {
    private EmailVerificationRepo emailVerificationRepo;

    @Autowired
    public void setEmailVerificationRepo(EmailVerificationRepo emailVerificationRepo) {this.emailVerificationRepo = emailVerificationRepo;}

    public String addEmailVerification(VerifyEmail verifyEmail) {
        emailVerificationRepo.save(verifyEmail);
        return verifyEmail.toString();
    };
}