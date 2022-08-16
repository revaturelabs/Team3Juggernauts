package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
<<<<<<< HEAD
import com.juggernauts.todoapp.repos.TaskRepo;
import com.juggernauts.todoapp.repos.UserRepo;
import com.juggernauts.todoapp.services.CategoryService;
import com.juggernauts.todoapp.services.TaskService;
=======
import com.juggernauts.todoapp.services.CategoryService;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;
=======
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563


@RestController
@RequestMapping("category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

<<<<<<< HEAD


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


        return ResponseEntity.ok(categoryService.getAllTasksPerCategory(currentUser));
    }
}
=======
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER");
        category.setUser(currentUser);

        categoryService.addCategory(category);
        return ResponseEntity.ok(category);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,List<Task>>> getAllCategories(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("USER");
        return ResponseEntity.ok(categoryService.getAllTasksPerCategory(currentUser));
    }
}
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
