package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

    @Query(value="From tasks where email like :email")
    List<User> findAll(@Param("type") String email);
}
