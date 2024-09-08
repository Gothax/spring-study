package com.gothaxcity.springjwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class RefreshEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String refreshToken;
    private String expiration;

    public RefreshEntity(String username, String refreshToken, String expiration) {
        this.username = username;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}