package com.juggernauts.todoapp.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private int userId;
    private String name;
    private boolean isDone;
    private int groupId;
    private int categoryId;
    private Date completeBy;
    private String description;

    public Task() {
    }

    public Task(int taskId, int userId, String name,
                boolean isDone, int groupId, int categoryId, Date completeBy, String description) {
        this.taskId = taskId;
        this.userId = userId;
        this.name = name;
        this.isDone = isDone;
        this.groupId = groupId;
        this.categoryId = categoryId;
        this.completeBy = completeBy;
        this.description = description;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(Date completeBy) {
        this.completeBy = completeBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", isDone=" + isDone +
                ", groupId=" + groupId +
                ", categoryId=" + categoryId +
                ", completeBy=" + completeBy +
                ", description='" + description + '\'' +
                '}';
    }
}

