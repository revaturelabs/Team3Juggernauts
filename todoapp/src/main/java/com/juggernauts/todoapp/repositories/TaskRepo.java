package com.juggernauts.todoapp.repositories;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
}