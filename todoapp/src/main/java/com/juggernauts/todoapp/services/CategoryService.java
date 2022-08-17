package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
<<<<<<< HEAD
import com.juggernauts.todoapp.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
public class CategoryService {
    private CategoryRepo categoryRepo;
    @Autowired
    public void setCategoryRepo(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Transactional()
    public int addCategory(Category category) {
        if (category.getCategoryName() != null) {
            categoryRepo.save(category);
            return 1;
        }
        return -1;
    }
}
=======
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.CategoryRepo;
import com.juggernauts.todoapp.repos.TaskRepo;
import com.juggernauts.todoapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    User currentUser;
    private CategoryRepo categoryRepo;

    @Autowired
    public void setCategoryRepo(CategoryRepo categoryRepo) {this.categoryRepo = categoryRepo;}


    public void addCategory(Category category) throws InvalidParameterException {
        try {
            categoryRepo.save(category);
        }
        catch (Exception e) {
            throw new InvalidParameterException();
        }

    }

    public List<Category> getAllCategoriesForUser(User currentUser) {
        List<Category> allCategories = categoryRepo.findAll();

        return allCategories.stream()
                .filter(category -> category.getUser().getId() == currentUser.getId())
                .collect(Collectors.toList());
    }
}
>>>>>>> f7791cd5abc766eb0f668e623c688b21a342ebcb
