package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.VerifyEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepo extends JpaRepository<VerifyEmail, Integer> {
}
