package com.juggernauts.todoapp.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
}