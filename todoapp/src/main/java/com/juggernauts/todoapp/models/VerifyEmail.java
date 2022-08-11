package com.juggernauts.todoapp.models;

import javax.persistence.*;

@Entity
@Table(name = "email_verification_tokens")
public class VerifyEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;
    private String token;
    private int userId;

    public VerifyEmail() {
    }

    public VerifyEmail(int tokenId, String token, int userId) {
        this.tokenId = tokenId;
        this.token = token;
        this.userId = userId;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VerifyEmail{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
