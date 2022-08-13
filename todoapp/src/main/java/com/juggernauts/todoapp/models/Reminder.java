package com.juggernauts.todoapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reminders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "reminder_id")
    private int reminderId;

    @Column(name = "task_id", nullable = false, columnDefinition = "INTEGER")
    private int taskId;

    @Column(name = "remind_by", nullable = false, columnDefinition = "DATE")
    private Date remindBy;
}
