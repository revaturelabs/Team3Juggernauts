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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @Column(name = "remind_by", nullable = false, columnDefinition = "DATE")
    private Date remindBy;

    @Column(name = "repeat_every", nullable = true, columnDefinition = "INTEGER")
    private Integer repeatEvery;
}