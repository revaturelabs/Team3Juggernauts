package com.juggernauts.todoapp.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Task {
    private int id;
    private String name;
    private String category;
    private Date completeBy;
    private boolean isDone;
    private String description; // Can be null, description not required during creation
    private int repeatsEvery;
    private int remindBy;
}
