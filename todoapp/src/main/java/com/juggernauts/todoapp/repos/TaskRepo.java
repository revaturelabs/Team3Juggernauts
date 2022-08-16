package com.juggernauts.todoapp.repos;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Category;
=======
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import com.juggernauts.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
}
