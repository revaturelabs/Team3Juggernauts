package com.juggernauts.todoapp.models;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "user_id")
    private int id;

    @Column(length=256, nullable=false, name = "password")
    private String password;

    @Column(length=256, nullable=false, unique=true, name = "email")
    private String email;

    @Column(columnDefinition = "boolean default false", name = "email_verified")
    private boolean verifyEmail;


}
