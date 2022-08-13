package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.repos.CategoryRepo;
import com.juggernauts.todoapp.repos.EmailVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {


    @Autowired
    public void setEmailVerificationRepo(EmailVerificationRepo emailVerificationRepo) {this.emailVerificationRepo = emailVerificationRepo;}
    private EmailVerificationRepo emailVerificationRepo;

    public String addEmailVerification(EmailVerification emailVerification) {
        emailVerificationRepo.save(emailVerification);
        return emailVerification.toString();
    }
}
