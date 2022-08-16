package com.juggernauts.todoapp.repos;

<<<<<<< HEAD
import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
=======
import com.juggernauts.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
<<<<<<< HEAD
=======
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String username);
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
}
