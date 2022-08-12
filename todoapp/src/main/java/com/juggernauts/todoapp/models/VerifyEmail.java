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
    private int tokenId;
    private String token;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}