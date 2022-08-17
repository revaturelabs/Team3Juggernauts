package com.juggernauts.todoapp.services;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.repositories.CategoryRepo;
import com.juggernauts.todoapp.repositories.EmailVerificationRepo;
=======
import com.juggernauts.todoapp.models.EmailVerification;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.EmailVerificationRepo;
>>>>>>> f7791cd5abc766eb0f668e623c688b21a342ebcb
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
<<<<<<< HEAD
    };
}
=======
    }

    public EmailVerification getEmailVerification(User user, String token) {
        return emailVerificationRepo.findOne(user, token);
    }

    public void deleteEmailVerification(EmailVerification ev) {
        emailVerificationRepo.delete(ev);
    }
}
>>>>>>> f7791cd5abc766eb0f668e623c688b21a342ebcb
