package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Reminder;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.ReminderRepo;
import com.juggernauts.todoapp.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    @Autowired
    public void setTaskRepo(TaskRepo taskRepo) {this.taskRepo = taskRepo;}

    public String addTask(Task task) {
        taskRepo.save(task);
        return task.toString();
    };

    public List<Task> getTasks(User user) {
        System.out.println("ENTERED GET TASKS!@#)($@)(*#$)(@#%@#*$@#)(*%");
        List<Task> allTasks = taskRepo.findAll();
//        System.out.println("ALL TASKS: "+allTasks);
//        List<Task> usersTasks = allTasks.stream()
//                .filter(task -> task.getUser().getId() == user.getId())
//                .collect(Collectors.toList());
        System.out.println("RETURNING ALL TASKS!!!!#%*)@&#^%*)@#^%(*@#)*^@!$*&@");
        return allTasks;
    }

        public HashMap<String, List<Task>> getAllTasksPerCategory(User currentUser) {
        // testing
        System.out.println("!*!*!*!*!*!*!*!*!*!*ENTERED TASK SERVICE!*!*!*!*!*!*!*!*!");

        List<Task> usersTasks = getTasks(currentUser);

        HashMap<String, List<Task>> tasksToCategory = null;

        // Put all incomplete tasks without category into None category
        List<Task> incompleteTasks = usersTasks.stream().filter(task -> task.getCategory() == null && !task.isDone()).collect(Collectors.toList());
        System.out.println("======================================INCOMPLETE TASKS:============================");
        incompleteTasks.forEach(System.out::println);
        tasksToCategory.put("None", incompleteTasks);

        // Put all complete tasks into Complete category
        List<Task> completeTasks = usersTasks.stream().filter(task -> task.isDone()).collect(Collectors.toList());
        System.out.println("======================================COMPLETE TASKS:============================");
        completeTasks.forEach(System.out::println);

        tasksToCategory.put("Complete", completeTasks);

        Set<String> categoryNames = null;
        List<Task> incompleteUncategorizedTasks = usersTasks.stream().filter(task -> !task.isDone() && task.getCategory() == null).collect(Collectors.toList());
        incompleteUncategorizedTasks.forEach(task -> categoryNames.add(task.getCategory().getCategoryName()));


        System.out.println("========================================CATEGORY NAMES:===========================");
        categoryNames.forEach(System.out::println);

        // go through all of users tasks putting each list of tasks per category in its appropriate category.
        categoryNames.forEach(
                categoryName -> {
                    List<Task> tasksPerCategory = usersTasks.stream()
                            .filter(task -> !task.isDone())
                            .filter(task -> task.getCategory().getCategoryName().equals(categoryName))
                            .collect(Collectors.toList());
                    System.out.println("******************CATEGORY NAME:****************");
                    tasksPerCategory.forEach(System.out::println);
                    tasksToCategory.put(categoryName, tasksPerCategory);
                }
        );

        return tasksToCategory;
    }
}
