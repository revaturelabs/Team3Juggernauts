package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
    public int deleteCategory(Category category) {
        boolean catExists = categoryRepo.existsById(category.getCategoryId());
        if (catExists) {
            categoryRepo.deleteById(category.getCategoryId());
            return 1;
        }
        return -1;
    }

    @Transactional
    public int updateCategory(int id, String name) {
        Category category = categoryRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("User with this id not found"));

        if(name != null && name.length() > 0 && !name.equals(category.getCategoryName())){
            category.setCategoryName(name);
            return 1;
        }
        return -1;
    }

    public int viewCategory(int id, String name){
        boolean categoryExists = categoryRepo.existsById(id);
        if(!categoryExists){
            return -1;
        }
        categoryRepo.findById(id);
        return 1;
    }

}

