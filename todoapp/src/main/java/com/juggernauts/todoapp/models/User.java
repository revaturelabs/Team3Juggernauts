package com.juggernauts.todoapp.models;

import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(nullable = false, columnDefinition = "VARCHAR(256)")
    private String password;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(256)")
    private String email;

    @Column(name = "email_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean emailVerified;

    public User(String password, String email, boolean emailVerified) {
        this.password = password;
        this.email = email;
        this.emailVerified = emailVerified;
    }
}
