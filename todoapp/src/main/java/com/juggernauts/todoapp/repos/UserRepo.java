package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
