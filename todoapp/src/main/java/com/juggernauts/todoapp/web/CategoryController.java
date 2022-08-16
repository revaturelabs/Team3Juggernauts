package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.UserRepo;
import com.juggernauts.todoapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }





    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewCategory(@RequestBody Category category) {
        System.out.println("Gotten CategoryName: "+category.getCategoryName());


//        User currentUser = userRepo.findById(1).get();

        User currentUser = new User(1, "testpassword", "testemail");
        System.out.println("Made user: " + currentUser);

        category.setUser(currentUser);
        System.out.println("Category w/ Current User: "+category);
        categoryService.addCategory(category);
        return ResponseEntity.ok(category);
    };
}