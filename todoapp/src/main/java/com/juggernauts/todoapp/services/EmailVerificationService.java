package com.juggernauts.todoapp.services;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.repos.CategoryRepo;
=======
import com.juggernauts.todoapp.models.EmailVerification;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import com.juggernauts.todoapp.repos.EmailVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {
    private EmailVerificationRepo emailVerificationRepo;

    @Autowired
    public void setEmailVerificationRepo(EmailVerificationRepo emailVerificationRepo) {this.emailVerificationRepo = emailVerificationRepo;}

    public String addEmailVerification(EmailVerification emailVerification) {
        emailVerificationRepo.save(emailVerification);
        return emailVerification.toString();
    };
}
