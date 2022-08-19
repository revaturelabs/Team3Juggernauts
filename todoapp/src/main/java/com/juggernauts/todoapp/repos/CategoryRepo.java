package com.juggernauts.todoapp.repos;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @Query("SELECT u FROM Category u WHERE u.categoryName = ?1")
    Category findOne(String categoryName);
}
