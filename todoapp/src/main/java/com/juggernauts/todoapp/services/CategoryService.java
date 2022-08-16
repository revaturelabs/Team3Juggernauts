package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.CategoryRepo;
import com.juggernauts.todoapp.repos.TaskRepo;
import com.juggernauts.todoapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    User currentUser;
    private CategoryRepo categoryRepo;

    @Autowired
    public void setCategoryRepo(CategoryRepo categoryRepo) {this.categoryRepo = categoryRepo;}


    public String addCategory(Category category) {
        categoryRepo.save(category);
        return category.toString();
    }


//    public HashMap<String, List<Task>> getAllTasksPerCategory(User currentUser) {
//        // testing
//        System.out.println("!*!*!*!*!*!*!*!*!*!*ENTERED CATEGORY SERVICE!*!*!*!*!*!*!*!*!");
//
//        List<Task> tasks = getTasks(currentUser);
//
//        //testing
//        tasks.forEach(System.out::println);
//
//        HashMap<String, List<Task>> tasksToCategory = null;
//
//        // Put all incomplete tasks without category into None category
//        List<Task> incompleteTasks = tasks.stream().filter(task -> task.getCategory() == null && !task.isDone()).collect(Collectors.toList());
//        System.out.println("======================================INCOMPLETE TASKS:============================");
//
//        incompleteTasks.forEach(System.out::println);
//        tasksToCategory.put("None", incompleteTasks);
//        // Put all complete tasks into Complete category
//        List<Task> completeTasks = tasks.stream().filter(task -> task.isDone()).collect(Collectors.toList());
//        System.out.println("======================================COMPLETE TASKS:============================");
//        completeTasks.forEach(System.out::println);
//
//        tasksToCategory.put("Complete", completeTasks);
//
//        // get list of all category names for user
//        List<String> categoryNames = categoryRepo.findAll()
//                .stream()
//                .filter(category -> category.getUser().getId() == currentUser.getId())
//                .map(Category::getCategoryName)
//                .collect(Collectors.toList());
//
//        System.out.println("========================================CATEGORY NAMES:===========================");
//        categoryNames.forEach(System.out::println);
//
//        // go through all of users tasks putting each list of tasks per category in its appropriate category.
//        categoryNames.forEach(
//                categoryName -> {
//                    List<Task> tasksPerCategory = tasks.stream()
//                            .filter(task -> !task.isDone())
//                            .filter(task -> task.getCategory().getCategoryName().equals(categoryName))
//                            .collect(Collectors.toList());
//                    System.out.println("******************CATEGORY NAME:****************");
//                    tasksPerCategory.forEach(System.out::println);
//                    tasksToCategory.put(categoryName, tasksPerCategory);
//                }
//        );
//
//        return tasksToCategory;
//    }
}
