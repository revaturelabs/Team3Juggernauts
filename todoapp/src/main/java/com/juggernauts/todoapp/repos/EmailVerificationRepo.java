package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepo extends JpaRepository<EmailVerification, Integer> {
}
