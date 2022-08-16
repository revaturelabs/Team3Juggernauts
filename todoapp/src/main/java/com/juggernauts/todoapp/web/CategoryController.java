package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

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