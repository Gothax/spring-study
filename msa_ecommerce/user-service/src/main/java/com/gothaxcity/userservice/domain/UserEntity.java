package com.gothaxcity.userservice.domain;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String encryptedPassword;
}
