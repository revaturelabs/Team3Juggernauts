package com.juggernauts.todoapp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "email_verification_tokens")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", columnDefinition = "INTEGER")
    private int tokenId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String token;

    @Column(name = "user_id",nullable = false, columnDefinition = "INTEGER")
    private int userId;
}
