package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Group;
import com.juggernauts.todoapp.models.Task;
import com.juggernauts.todoapp.models.User;
import com.juggernauts.todoapp.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepo taskRepo;
    private User user;
    private Group group;
    private Category category;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public TaskService() {
    }

    @Autowired
    public void setTaskRepo(TaskRepo taskRepo) {this.taskRepo = taskRepo;}

    @Transactional
    public int addTask(Task task) {
        if (task.getTaskId() > 0){
            taskRepo.save(task);
            return 1;
        }
        return-1;
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

    public List<Task> getAllTasksByUserId(List<Integer> ids) {
        List<Task> tasksById = taskRepo.findAllById(ids);
        if (!tasksById.isEmpty()) {
            return tasksById;
        }
        return null;
    }

    public List<Task> getAllTasksUnCompleteByUserId(Task task, int id) {
        List<Task> allTasks = taskRepo.findAll();
       List<Task> incompleteTasksByUser = allTasks.stream().filter(Task -> !task.isDone() && user.getId() == id)
                .collect(Collectors.toList());
        if(!incompleteTasksByUser.isEmpty()){
            return incompleteTasksByUser;
        }
        return null;
    }

    public List<Task> getAllTasksCompleteByUserId(Task task, int id) {
        List<Task> allTasks = taskRepo.findAll();
        List<Task> completeTasksByUser = allTasks.stream().filter(Task -> task.isDone() && user.getId() == id)
                .collect(Collectors.toList());
        if(!completeTasksByUser.isEmpty()){
            return completeTasksByUser;
        }
        return null;
    }

    public List<Task> getAllTasksByGroup (Task task, int id) {
        List<Task> allTasks = taskRepo.findAll();
    List<Task> groupTasks = allTasks.stream().filter(Task -> group.getGroupId() == id)
            .collect(Collectors.toList());
    if(!groupTasks.isEmpty()){
        return groupTasks;
    }
    return null;
    }

    public List<Task> getAllTasksByCategory (Task task, int id) {
        List<Task> allTasks = taskRepo.findAll();
        List<Task> categoryTasks = allTasks.stream().filter(Task -> category.getCategoryId() == id)
                .collect(Collectors.toList());
        if(!categoryTasks.isEmpty()){
            return categoryTasks;
        }
        return null;
    }
}
