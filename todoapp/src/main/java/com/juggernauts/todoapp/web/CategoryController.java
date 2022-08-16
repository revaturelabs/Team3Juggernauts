package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.UserRepo;
import com.juggernauts.todoapp.services.CategoryService;
import com.juggernauts.todoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("category")
public class CategoryController {
    CategoryService categoryService;
    TaskService taskService;

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
        System.out.println(category);

        // This will be DELETED once currentUser is available from userSession
        User currentUser = new User(1, "testpass1", "test1");
//        System.out.println("Made user: " + currentUser);


        // This will all STAY once currentUser is available from userSession
        category.setUser(currentUser);


        categoryService.addCategory(category);
        return ResponseEntity.ok(category);
    };

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCategories() {
        System.out.println("!*!*!*!*!*!*!*!*!*&!*!*!*ENTERED CATEGORY CONTROLLER!*!*!*!**!*!*!*!*!*!*");
        // This will be DELETED once currentUser is available from userSession
        User currentUser = new User(1, "testpass1", "test1");
        System.out.println("CURRENT USER: "+currentUser);


        return ResponseEntity.ok(taskService.getAllTasksPerCategory(currentUser));
    }
}