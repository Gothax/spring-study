package com.gothaxcity.springbatchdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
public class AfterEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

}
