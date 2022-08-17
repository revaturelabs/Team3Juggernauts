package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public String addTask(Task task) {
        taskRepo.save(task);
        return task.toString();
    };

    public List<Task> getTasks(User user) {
        System.out.println("******************* ENTERED GET TASKS! *******************");
        List<Task> allTasks = taskRepo.findAll();
        System.out.println("ALL TASKS: "+allTasks);
        List<Task> usersTasks = allTasks.stream()
                .filter(task -> task.getUser().getId() == user.getId())
                .collect(Collectors.toList());
        System.out.println("USERS TASKS: ");
        usersTasks.forEach(System.out::println);
        System.out.println("******************* END OF USERS TASKS *******************");
        System.out.println("******************* RETURNING USER TASKS!!!! *******************");
        return usersTasks;
    }

    public HashMap<String, List<Task>> getAllTasksPerCategory(User currentUser) {
        // testing
        System.out.println("******************* ENTERED TASK SERVICE! *******************");

        List<Task> usersTasks = getTasks(currentUser);

        HashMap<String, List<Task>> tasksToCategory = new HashMap<>();

        // Put all incomplete tasks without category into None category
        List<Task> incompleteTasks = usersTasks.stream().filter(task -> task.getCategory() == null && !task.isDone())
                .collect(Collectors.toList());
        System.out.println("======================================INCOMPLETE TASKS:============================");
        incompleteTasks.forEach(System.out::println);
        tasksToCategory.put("None", incompleteTasks);
        System.out.println("******************* INCOMPLETE TASKS ADDED TO HASHMAP *******************");

        System.out.println(tasksToCategory);

        // Put all complete tasks into Complete category
        List<Task> completeTasks = usersTasks.stream().filter(task -> task.isDone()).collect(Collectors.toList());
        System.out.println("======================================COMPLETE TASKS:============================");
        completeTasks.forEach(System.out::println);

        tasksToCategory.put("Complete", completeTasks);
        System.out.println("COMPLETE TASKS ADDED TO HASHMAP");

        System.out.println(tasksToCategory);

        Set<String> categoryNames = new HashSet<>();
        List<Task> incompleteCategorizedTasks = usersTasks.stream().filter(task -> !task.isDone() && task.getCategory() != null).collect(Collectors.toList());
        incompleteCategorizedTasks.forEach(task -> categoryNames.add(task.getCategory().getCategoryName()));


        System.out.println("======================================== CATEGORY NAMES: ===========================");
        categoryNames.forEach(System.out::println);

        // go through all of users tasks putting each list of tasks per category in its appropriate category.
        categoryNames.forEach(
                categoryName -> {
                    System.out.println("Category name: " +categoryName);
                    List<Task> tasksPerCategory = incompleteCategorizedTasks.stream()
                            .filter(task -> task.getCategory().getCategoryName().equals(categoryName))
                            .collect(Collectors.toList());
                    System.out.println("****************** CATEGORY NAME: ****************");
                    tasksPerCategory.forEach(System.out::println);
                    tasksToCategory.put(categoryName, tasksPerCategory);
                }
        );

        return tasksToCategory;
    }
}