package com.juggernauts.todoapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "user_id",nullable = false, columnDefinition = "INTEGER")
    private int userId;

    @Column(nullable = false, columnDefinition = "VARCHAR(32)")
    private String name;

    @Column(name = "is_done", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDone;

    @Column(name = "group_id", columnDefinition = "INTEGER")
    private int groupId;

    @Column(name = "category_id", columnDefinition = "INTEGER")
    private int categoryId;

    @Column(name = "complete_by", columnDefinition = "DATE")
    private Date completeBy;

    @Column(columnDefinition = "TEXT")
    private String description;
}
