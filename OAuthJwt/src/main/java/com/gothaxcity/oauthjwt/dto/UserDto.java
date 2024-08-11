package com.gothaxcity.oauthjwt.dto;

import lombok.Data;

@Data
public class UserDto {
    private String role;
    private String name;
    private String username;

    public UserDto(String role, String name, String username) {
        this.role = role;
        this.name = name;
        this.username = username;
    }
}
