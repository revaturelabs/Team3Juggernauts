package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Group;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.CategoryRepo;
import com.juggernauts.todoapp.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepo taskRepo;
  private CategoryRepo categoryRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public TaskService() {
    }

    @Autowired
    public void setTaskRepo(TaskRepo taskRepo) {this.taskRepo = taskRepo;}

    @Transactional
    public int addTask(Task task) {
        if ( task == null || !(task.getTaskId() >= 0)){
            return -1;
        }
        taskRepo.save(task);
        return 1;
    }

    @Transactional
    public int updateIsDone(Task task ) {
        if (!taskRepo.existsById(task.getTaskId())){
            return -1;
        }
        task.setDone(!task.isDone());
        return 1;
    }

    @Transactional
    public int deleteTask(int id) {
        boolean taskExists = taskRepo.existsById(id);
        if (taskExists) {
            taskRepo.deleteById(id);
            return 1;
        }
        return -1;
    }

    public int getTask(int id){
        boolean taskExists = taskRepo.existsById(id);
        if(taskExists){
            taskRepo.findById(id);
            return 1;
        }
        return -1;
    }
    public List<Task> getTasks(User user) {
        System.out.println("ENTERED .getTasks()");
        List<Task> allTasks = taskRepo.findAll();
        System.out.println("ALL TASKS: " + allTasks);
        List<Task> usersTasks = allTasks.stream()
                .filter(task -> task.getUser().getId() == user.getId())
                .collect(Collectors.toList());
        return usersTasks;
    }

    public HashMap<String, List<Task>> getAllTasksPerCategory(User currentUser) {
        // testing
        System.out.println("!*!*!*!*!*!*!*!*!*!*ENTERED CATEGORY SERVICE!*!*!*!*!*!*!*!*!");

        List<Task> tasks = getTasks(currentUser);

        //testing
        tasks.forEach(System.out::println);

        HashMap<String, List<Task>> tasksToCategory = null;

        // Put all incomplete tasks without category into None category
        List<Task> incompleteTasks = tasks.stream().filter(task -> task.getCategory() == null && !task.isDone()).collect(Collectors.toList());
        System.out.println("======================================INCOMPLETE TASKS:============================");

        incompleteTasks.forEach(System.out::println);
        tasksToCategory.put("None", incompleteTasks);
        // Put all complete tasks into Complete category
        List<Task> completeTasks = tasks.stream().filter(task -> task.isDone()).collect(Collectors.toList());
        System.out.println("======================================COMPLETE TASKS:============================");
        completeTasks.forEach(System.out::println);

        tasksToCategory.put("Complete", completeTasks);

        // get list of all category names for user
        List<String> categoryNames = categoryRepo.findAll()
                .stream()
                .filter(category -> category.getUser().getId() == currentUser.getId())
                .map(Category::getCategoryName)
                .collect(Collectors.toList());

        System.out.println("========================================CATEGORY NAMES:===========================");
        categoryNames.forEach(System.out::println);

        // go through all of users tasks putting each list of tasks per category in its appropriate category.
        categoryNames.forEach(
                categoryName -> {
                    List<Task> tasksPerCategory = tasks.stream()
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


//    public List<Task> getAllTasksByUserId(List<Integer> ids) {
//        List<Task> tasksById = taskRepo.findAllById(ids);
//        if (!tasksById.isEmpty()) {
//            return tasksById;
//        }
//        return null;
//    }
//
//    public List<Task> getAllTasksUnCompleteByUserId(Task task, int id) {
//        List<Task> allTasks = taskRepo.findAll();
//       List<Task> incompleteTasksByUser = allTasks.stream().filter(Task -> !task.isDone() && user.getId() == id)
//                .collect(Collectors.toList());
//        if(!incompleteTasksByUser.isEmpty()){
//            return incompleteTasksByUser;
//        }
//        return null;
//    }
//
//    public List<Task> getAllTasksCompleteByUserId(Task task, int id) {
//        List<Task> allTasks = taskRepo.findAll();
//        List<Task> completeTasksByUser = allTasks.stream().filter(Task -> task.isDone() && user.getId() == id)
//                .collect(Collectors.toList());
//        if(!completeTasksByUser.isEmpty()){
//            return completeTasksByUser;
//        }
//        return null;
//    }
//
//    public List<Task> getAllTasksByGroup (Task task, int id) {
//        List<Task> allTasks = taskRepo.findAll();
//    List<Task> groupTasks = allTasks.stream().filter(task1 -> group.getGroupId() == id)
//            .collect(Collectors.toList());
//    if(!groupTasks.isEmpty()){
//        return groupTasks;
//    }
//    return null;
//    }
//
//    public List<Task> getAllTasksByCategory (Task task, int id) {
//        List<Task> allTasks = taskRepo.findAll();
//        List<Task> categoryTasks = allTasks.stream().filter(task1 -> category.getCategoryId() == id)
//                .collect(Collectors.toList());
//        if(!categoryTasks.isEmpty()){
//            return categoryTasks;
//        }
//        return null;
//    }
}
