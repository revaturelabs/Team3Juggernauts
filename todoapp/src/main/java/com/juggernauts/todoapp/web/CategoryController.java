package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.configurations.interceptors.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;
import java.util.List;


@RestController
@RequestMapping("category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewCategory(@RequestBody Category category, HttpServletRequest request) {

        User currentUser = (User) request.getSession().getAttribute("USER");

        category.setUser(currentUser);

        try {
            categoryService.addCategory(category);
            return ResponseEntity.ok(category);
        }
        catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().body("Invalid category name");
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCategories(@RequestBody HttpServletRequest request) {
        // Testing
        User currentUser = (User) request.getSession().getAttribute("USER");

        List<Category> categories = categoryService.getAllCategoriesForUser(currentUser);
        return ResponseEntity.ok(categories);
    }

}
