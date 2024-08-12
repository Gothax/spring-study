package com.gothaxcity.oauthjwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    private String role;
    private String name;
    private String username;

    @Builder
    public UserDto(String username, String name, String role) {
        this.role = role;
        this.name = name;
        this.username = username;
    }
}
