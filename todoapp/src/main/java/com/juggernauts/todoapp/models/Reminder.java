package com.juggernauts.todoapp.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int remindId;
    private int taskId;
    private Date remindBy;

    public Reminder() {
    }

    public Reminder(int remindId, int taskId, Date remindBy) {
        this.remindId = remindId;
        this.taskId = taskId;
        this.remindBy = remindBy;
    }

    public int getRemindId() {
        return remindId;
    }

    public void setRemindId(int remindId) {
        this.remindId = remindId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Date getRemindBy() {
        return remindBy;
    }

    public void setRemindBy(Date remindBy) {
        this.remindBy = remindBy;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "remindId=" + remindId +
                ", taskId=" + taskId +
                ", remindBy=" + remindBy +
                '}';
    }
}
