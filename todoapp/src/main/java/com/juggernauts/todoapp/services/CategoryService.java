package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.CategoryRepo;
import com.juggernauts.todoapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    User currentUser;
    private CategoryRepo categoryRepo;

    @Autowired
    public void setCategoryRepo(CategoryRepo categoryRepo) {this.categoryRepo = categoryRepo;}


    private UserRepo userRepo;
    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public String addCategory(Category category) {
        System.out.println("About to add category: "+ category);
        categoryRepo.save(category);
        return category.toString();
    };
}
